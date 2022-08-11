package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;

import java.util.ArrayList;

/**
 * Node of the AST for an identifier
 *
 */
public class IdNode implements Node {

    final private String id;

    public IdNode(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "ID: " + id;
    }

    @Override
    public String toString() {
        return toPrint("");
    }

    // will be called only when id is used not declared
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> err = new ArrayList<>();

        if (env.lookup(id) == null) { // may do this control with try catch and a specific exception
            err.add(new SemanticError("Missing declaration for ID: " + id));
        }

        return err;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
