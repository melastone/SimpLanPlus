package it.ghellimanca.ast.exp;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.BoolTypeNode;

import java.util.ArrayList;

/**
 * Represents a boolean expression node in the AST.
 */
public class BoolExpNode extends ExpNode {
    final boolean bool;

    public BoolExpNode(boolean bool) {
        this.bool = bool;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return new BoolTypeNode();
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "BOOL_EXP: " + bool;
    }
}
