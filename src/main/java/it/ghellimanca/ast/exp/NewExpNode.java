package it.ghellimanca.ast.exp;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;

/**
 * Represents the new operator as a node in the AST.
 */
public class NewExpNode extends ExpNode {
    final TypeNode type;

    public NewExpNode(TypeNode type) {
        this.type = type;
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
