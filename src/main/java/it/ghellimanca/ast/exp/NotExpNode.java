package it.ghellimanca.ast.exp;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;

import java.util.ArrayList;

/**
 * Represents a boolean expression node in the AST being negated with the NOT boolean operator.
 */
public class NotExpNode extends ExpNode {
    final ExpNode exp;

    public NotExpNode(ExpNode exp) {
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
