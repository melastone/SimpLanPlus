package it.ghellimanca.ast.exp;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.type.IntTypeNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

import java.util.ArrayList;

/**
 * Represents an negated expression '-' exp node in the AST.
 */
public class NegExpNode extends ExpNode {

    final ExpNode exp;


    public NegExpNode(ExpNode exp) {
        this.exp = exp;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "NEG_EXP" + exp.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return exp.checkSemantics(env);
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        // checking that the expression returns an int to be negated
        if (!(exp.typeCheck() instanceof IntTypeNode))
            throw new TypeCheckingException("Expression must be of type int to be negated.");

        return new IntTypeNode();
    }
}
