package it.ghellimanca.ast.exp;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.BoolTypeNode;
import it.ghellimanca.ast.type.IntTypeNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

import java.util.ArrayList;

/**
 * Represents an value expression node in the AST being negated.
 */
public class NegExpNode extends ExpNode {
    final ExpNode exp;

    public NegExpNode(ExpNode exp) {
        this.exp = exp;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        // checking that the expression returns an int to be negated
        if (!(exp.typeCheck() instanceof IntTypeNode))
            throw new TypeCheckingException("Expression must be of type int to be negated.");

        return new IntTypeNode();
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "NEG_EXP" + exp.toPrint(indent + "\t");
    }
}
