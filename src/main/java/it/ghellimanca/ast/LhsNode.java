package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;

import java.util.ArrayList;

/**
 * Represents a left-hand side expression node in the AST.
 */
public class LhsNode implements Node {
    final IdNode id;
    final LhsNode lhs; //could be null

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

    @Override
    public String toPrint(String indent) {
        if (lhs != null) {
            return "\n" + indent + "LHS" + lhs.toPrint(indent + "\t");
        }
        else {
            return "\n" + indent + id.toPrint(indent + "\t");
        }
    }

    @Override
    public String toString() {
        return toPrint("");
    }
}
