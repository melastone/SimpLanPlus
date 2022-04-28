package it.ghellimanca.ast.exp;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.LhsNode;
import it.ghellimanca.ast.Node;

import java.util.ArrayList;

/**
 * Represents the result of dereferencing something as a node in the AST.
 */
public class DerExpNode extends ExpNode {
    final LhsNode lhs;

    public DerExpNode(LhsNode lhs) {
        this.lhs = lhs;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
