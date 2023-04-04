package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.type.BoolTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.errors.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Node of the AST for an iteration statement
 *
 * An ite statement has the form:
 * 'if' '(' exp ')' statement ('else' statement)?
 *
 */
public class IteNode extends StatementNode {

    final private ExpNode exp;
    final private StatementNode stm1;
    final private StatementNode stm2;



    public IteNode(ExpNode exp, StatementNode stm1, StatementNode stm2) {
        this.exp = exp;
        this.stm1 = stm1;
        this.stm2 = stm2;
    }

    public IteNode(ExpNode exp, StatementNode stm1) {
        this.exp = exp;
        this.stm1 = stm1;
        this.stm2 = null;
    }



    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "ITE" + exp.toPrint(indent + "\t") + stm1.toPrint(indent + "\t");

        if (this.stm2 != null) {
            res += stm2.toPrint(indent + "\t");
        }
        return res;
    }


    @Override
    public String toString() { return toPrint("");}

    @Override
    public void setFunId(String funId){
        stm1.setFunId(funId);
        if (stm2 != null) {
            stm2.setFunId(funId);
        }
    }


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        ArrayList<SemanticWarning> err = new ArrayList<>();

        err.addAll(exp.checkSemantics(env));


        if(this.stm2 == null) {     // else branch empty
            err.addAll(stm1.checkSemantics(env));
        } else {                    // else branch not empty
            Environment thenBranchEnv = new Environment(env);   // copy of env for then branch eval; it will change with results of its eval
            err.addAll(stm1.checkSemantics(thenBranchEnv));

            Environment elseBranchEnv = new Environment(env);   // copy of env for else branch eval
            err.addAll(stm2.checkSemantics(elseBranchEnv));

            // bin fra thenbranch e elsebranch
            // replace env con risultato bin
            thenBranchEnv.bin(elseBranchEnv);
            env.replace(thenBranchEnv);
        }

        return err;

    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        TypeNode condType = exp.typeCheck();
        TypeNode stm1Type = stm1.typeCheck();

        // checking that the condition is a boolean expression
        if(!(condType instanceof BoolTypeNode))
            throw new TypeCheckingException("Condition of the if statement is not of type bool.");

        // checking that if there is an else branch it's the same type as the then branch
        if (this.stm2 != null && !(stm1Type.equals(stm2.typeCheck()))) {
            throw new TypeCheckingException("Then and else branch of the if statement have different types.");
        }

        // returning the branches type
        return stm1Type;
    }

    @Override
    public String codeGeneration() {
        StringBuilder buff = new StringBuilder();
        Boolean hasElse = !(stm2 == null);

        String falseBranch = "FALSE_BRANCH";
        String endIf = "END_IF";

        buff.append(exp.codeGeneration());
        buff.append("li $t1 0\n");
        buff.append("beq $t1 $a0 ").append(falseBranch).append("\n");

        buff.append(stm1.codeGeneration());
        buff.append("b ").append(endIf).append('\n');

        buff.append(falseBranch).append(":\n");

        if (hasElse) {
          buff.append(stm2.codeGeneration());
        }

        buff.append(endIf).append(":\n");

        return buff.toString();
    }

    @Override
    public List<IdNode> variables() {
        List<IdNode> tmp = new ArrayList<>();

        tmp.addAll(exp.variables());
        tmp.addAll(stm1.variables());
        tmp.addAll(stm2 != null ? stm2.variables() : new ArrayList<>());

        return tmp;
    }

    @Override
    public List<IdNode> getVarDeclarations() {
        List<IdNode> tmp = new ArrayList<>();
        
        tmp.addAll(stm1.getVarDeclarations());
        tmp.addAll(stm2 != null ? stm2.getVarDeclarations() : new ArrayList<>());
        
        return tmp;
    }
}
