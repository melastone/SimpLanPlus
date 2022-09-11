package it.ghellimanca.ast.exp;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.type.BoolTypeNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

import java.util.ArrayList;


/**
 * Represents a boolean negated expression '!' exp node in the AST.
 */
public class NotExpNode extends ExpNode {

    final ExpNode exp;


    public NotExpNode(ExpNode exp) {
        this.exp = exp;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "NOT_EXP" + exp.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return exp.checkSemantics(env);
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        // checking that the expression returns a bool to be negated with the NOT operator
        if (!(exp.typeCheck() instanceof BoolTypeNode))
            throw new TypeCheckingException("Expression must be of type bool to be negated with the NOT operator.");

        return new BoolTypeNode();
    }
}
