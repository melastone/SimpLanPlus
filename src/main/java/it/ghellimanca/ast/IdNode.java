package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.semanticanalysis.MissingDeclarationException;

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

        try {
            env.lookup(id);
        } catch(MissingDeclarationException exception) {
            err.add(new SemanticError(exception.getMessage()));
        }

        return err;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
