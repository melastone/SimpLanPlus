package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;

import java.util.ArrayList;

public class IdNode implements Node {
    final String id;

    public IdNode(String id) {
        this.id = id;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "ID: " + id;
    }

    @Override
    public String toString() {
        return toPrint("");
    }
}
