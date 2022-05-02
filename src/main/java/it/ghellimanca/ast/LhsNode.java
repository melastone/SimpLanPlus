package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;

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
