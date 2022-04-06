package it.ghellimanca.ast.exp;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;

import java.util.ArrayList;

/**
 * Represents a base expression '(' + expr + ')' node  in the AST.
 */
public class BaseExpNode extends ExpNode {
    final ExpNode exp;

    public BaseExpNode(ExpNode exp) {
        this.exp = exp;
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
