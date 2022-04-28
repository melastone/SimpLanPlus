package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;

import java.util.ArrayList;

/**
 * Represents a left-hand side expression node in the AST.
 */
public class LhsNode implements Node {
    final int idNodeid; //TODO: cambiare ID LhsNode quando abbiamo capito impl ID node
    final LhsNode lhs;

    public LhsNode(int idNodeid, LhsNode lhs) {
        this.idNodeid = idNodeid;
        this.lhs = lhs;
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
