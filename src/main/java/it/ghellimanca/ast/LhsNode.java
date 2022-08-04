package it.ghellimanca.ast;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;

import java.util.ArrayList;

/**
 * Represents a left-hand side expression node in the AST.
 *
 * An lhs has the form:
 * ID | lhs '^'
 *
 */
public class LhsNode implements Node {

    final private IdNode id;
    final LhsNode lhs;


    public LhsNode(IdNode id, LhsNode lhs) {
        this.id = id;
        this.lhs = lhs;
    }

    public IdNode getId() {
        return id;
    }

    public LhsNode getLhs() {
        return lhs;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "LHS";

        if (lhs != null) {
            res += lhs.toPrint(indent + "\t");
        }
        else {
            res += id.toPrint(indent + "\t");
        }

        return res;
    }

    @Override
    public String toString() {
        return toPrint("");
    }

    //TODO da implementare checkSemantics di LhsNode
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    //TODO da implementare typeCheck di LhsNode
    @Override
    public Node typeCheck() {
        return null;
    }
}
