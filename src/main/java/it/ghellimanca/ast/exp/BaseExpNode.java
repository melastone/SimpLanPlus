package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.errors.*;

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
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        return exp.checkSemantics(env);
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        return exp.typeCheck();
    }

    @Override
    public String codeGeneration() {
        return exp.codeGeneration();
    }


    @Override
    public List<IdNode> variables() {
        return exp.variables();
    }
}
