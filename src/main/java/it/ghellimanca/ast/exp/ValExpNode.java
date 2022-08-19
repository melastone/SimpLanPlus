package it.ghellimanca.ast.exp;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.IntTypeNode;
import it.ghellimanca.ast.type.TypeNode;

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
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public TypeNode typeCheck() {
        return new IntTypeNode();
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "VAL_EXP: " + number;
    }
}
