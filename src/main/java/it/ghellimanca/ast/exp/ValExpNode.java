package it.ghellimanca.ast.exp;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.IntTypeNode;

import java.util.ArrayList;

/**
 * Represents a integer expression node in the AST.
 */
public class ValExpNode extends ExpNode {

    final int number;

    public ValExpNode(int number) {
        this.number = number;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "VAL_EXP: " + number;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public Node typeCheck() {
        return new IntTypeNode();
    }
}
