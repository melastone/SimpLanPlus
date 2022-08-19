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
        return new ArrayList<>();
    }

    //@todo implementare typecheck newexp
    //@todo capire come funziona new
    @Override
    public TypeNode typeCheck() {
        return null;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "NEW_EXP" + type.toPrint(indent + "\t");
    }
}
