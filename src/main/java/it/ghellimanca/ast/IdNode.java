package it.ghellimanca.ast;


import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.ast.type.VarTypeNode;
import it.ghellimanca.semanticanalysis.*;

import java.util.ArrayList;


/**
 * Node of the AST for an identifier
 *
 */
public class IdNode implements Node {

    final private String id;

    private STEntry stEntry;



    public IdNode(String id) {
        this.id = id;
    }


    public String getIdentifier() {
        return id;
    }

    public STEntry getStEntry() {
        return stEntry;
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
            stEntry = env.lookup(id); // lookup has to return the type of the value that the id identifies
        } catch(MissingDeclarationException exception) {
            err.add(new SemanticError(exception.getMessage()));
        }

        return err;
    }

    @Override
    public TypeNode typeCheck() {
        TypeNode type = stEntry.getType();

        if (type instanceof VarTypeNode) type = ((VarTypeNode) type).getType();

        return type;
    }

}
