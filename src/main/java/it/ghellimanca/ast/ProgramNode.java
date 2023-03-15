package it.ghellimanca.ast;

import it.ghellimanca.ast.declaration.DeclarationNode;
import it.ghellimanca.ast.statement.IteNode;
import it.ghellimanca.ast.statement.ReturnNode;
import it.ghellimanca.ast.statement.StatementNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.ast.type.VoidTypeNode;
import it.ghellimanca.semanticanalysis.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO: distinzione tra dichiarazioni di vars e di fun nella codegen
// TODO: aggiornare RA nella codegen una volta capito come

public class ProgramNode implements Node {

    final private List<DeclarationNode> declarations;
    final private List<StatementNode> statements;



    public ProgramNode(List<DeclarationNode> declarations, List<StatementNode> statements) {
        this.declarations = declarations;
        this.statements = statements;
    }



    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "PROGRAM";

        if (this.declarations != null) {
            for (DeclarationNode dec : declarations) {
                res += dec.toPrint(indent + "\t");
            }
        }

        if (this.statements != null) {
            for (StatementNode stat : statements) {
                res += stat.toPrint(indent + "\t");
            }
        }

        return res;
    }


    @Override
    public String toString() {
        return toPrint("");
    }


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersCountException {

        ArrayList<SemanticWarning> err = new ArrayList<>();
        Map<String, STEntry> currentScope;

        env.newScope();

        if (this.declarations != null) {
            for (DeclarationNode dec : declarations) {
                err.addAll(dec.checkSemantics(env));
            }
        }

        if (this.statements != null) {
            for (StatementNode stat : statements) {
                err.addAll(stat.checkSemantics(env));
            }
        }

        // CHECK ERRORS IN BLOCK FOR EFFECT ANALYSIS

        // getting sigma_1' from sigma' = sigma_0' ⋅ sigma_1'
        currentScope = env.currentScope();

        // for every x_i in dom(sigma_1')
        for (var id : currentScope.keySet()) {

            var idStatus = currentScope.get(id).getVarStatus();

            // sigma_1'(x) has to be <= USED otherwise there was an error
            if (idStatus.equals(new Effect(Effect.ERROR))) {
                throw new MissingInitializationException("Error in the effect analysis: " + id + " was used before initialization.");
            } else if (idStatus.equals(new Effect(Effect.INIT)) || idStatus.equals(new Effect(Effect.DECLARED))){
                err.add(new SemanticWarning("Variable " + id + " was declared but never used."));
            }
        }

        // qui potremmo prendere l'offset dello scope attuale, che corrisponde a n
        // ci serve conoscere n per la codegeneration
        // in realtà non c'è bisogno; potrei delegare la sottrazione di 1 ad ogni code generation dei DEcVarNode

        // returning just sigma_0'
        env.exitScope();

        return err;
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        if (this.declarations != null) {
            for (DeclarationNode dec : declarations) {
                dec.typeCheck();
            }
        }

        if (this.statements != null) {

            // gathering the eventual itenode/blocknode/returnnode types inside the block in a list
            ArrayList<TypeNode> stmTypesRet = new ArrayList<>();

            for (StatementNode stat : statements) {
                if (stat instanceof ReturnNode || stat instanceof IteNode || stat instanceof BlockNode){
                    TypeNode stmTypeRet = stat.typeCheck();
                    stmTypesRet.add(stmTypeRet);
                }
                else {
                    stat.typeCheck(); // always continuing the typecheck anyway
                }
            }

            // if there are multiple return types from the nodes that can return types that are not void
            if(stmTypesRet.size() > 0) {
                for (int i = 0; i < stmTypesRet.size() - 1; i++) {

                    // we check that they are the same
                    if (!(stmTypesRet.get(i).equals(stmTypesRet.get(i + 1)))) {
                        throw new TypeCheckingException("Different types to return.");
                    }
                }

                // returning the common type
                return stmTypesRet.get(0);
            }
        }

        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration() {
        StringBuilder buff = new StringBuilder();

        // suppongo $sp e $fp già inizializzati a memsize (lo si fa nella vera e propria SVM)

        // check before that decs are not null!

        buff.append("subi $sp $sp").append(declarations.size());
        buff.append("li $t0 0");    // using a fake RA
        buff.append("push $t0");
        buff.append("push $fp");
        buff.append("move $fp $sp");

        for (DeclarationNode dec : declarations) {
                buff.append(dec.codeGeneration());
        }

        if (this.statements != null) {
            for (StatementNode stm : statements) {
                buff.append(stm.codeGeneration());
            }
        }

        // is there anything we have to do??? setting fp and sp to memsize, for instance...

        return buff.toString();
    }
}
