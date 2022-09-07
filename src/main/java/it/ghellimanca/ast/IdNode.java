package it.ghellimanca.ast;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;

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

    public String getIdentifier() {
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

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
