package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a base expression '(' exp ')' node  in the AST.
 */
public class BaseExpNode extends ExpNode {

    final ExpNode exp;


    public BaseExpNode(ExpNode exp) {
        this.exp = exp;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "BASE_EXP" + exp.toPrint(indent + "\t");
    }


    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) throws MissingInitializationException, ParametersCountException {
        return exp.checkSemantics(env);
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        return exp.typeCheck();
    }


    @Override
    public List<IdNode> variables() {
        return exp.variables();
    }
}
