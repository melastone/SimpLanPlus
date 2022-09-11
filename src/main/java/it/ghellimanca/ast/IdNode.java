package it.ghellimanca.ast;


import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.MissingDeclarationException;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import java.util.ArrayList;


/**
 * Node of the AST for an identifier
 *
 */
public class IdNode implements Node {

    final private String id;
    private TypeNode entryType;

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

    // will be called only when id is used not declared
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> err = new ArrayList<>();

        try {
            entryType = env.lookup(id); // lookup has to return the type of the value that the id identifies
        } catch(MissingDeclarationException exception) {
            err.add(new SemanticError(exception.getMessage()));
        }

        return err;
    }

    @Override
    public TypeNode typeCheck() {
        return entryType;
    }
}
