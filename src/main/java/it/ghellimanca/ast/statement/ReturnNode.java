package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.type.VoidTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.errors.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Node of the AST for a return statement
 *
 * A return statement has the form:
 * 'return' (exp)?
 *
 * todo: si potrebbe aggiungere il controllo x warning in caso di codice scritto dopo una return....
 */
public class ReturnNode extends StatementNode {

    final private ExpNode exp;

    private String funId;



    public ReturnNode() {
        this.exp = null;
    }


    public ReturnNode(ExpNode exp) {
        this.exp = exp;
    }



    public void setFunId(String funId) {
        this.funId = funId;
    }


    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "RET";
        if (this.exp != null) {
            res += exp.toPrint(indent + "\t");
        }
        return res;
    }


    @Override
    public String toString() { return toPrint("");}


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        if (this.exp != null) {
            return exp.checkSemantics(env);
        }

        return new ArrayList<>();
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        // if there is an expression we return its type
        if (this.exp != null) {
            return exp.typeCheck();
        }

        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration() {
        StringBuilder buff = new StringBuilder();

        var funId = this.funId.toUpperCase();

        if (exp != null) {
            buff.append(exp.codeGeneration());
        }

        // if there is code written after the return statement, it could affect $a0 status
        // simply ignore it!
        buff.append("b ").append(funId).append("_END\n");

        return buff.toString();
    }

    @Override
    public List<IdNode> variables() {
        return exp != null ? exp.variables() : new ArrayList<>();
    }

    @Override
    public List<IdNode> getVarDeclarations() {
        return new ArrayList<>();
    }
}
