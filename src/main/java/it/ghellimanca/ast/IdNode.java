package it.ghellimanca.ast;


import it.ghellimanca.ast.type.TypeNode;
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

    public String getId() {
        return id;
    }

    public STEntry getStEntry() {
        return stEntry;
    }

    public void setStEntry(STEntry stEntry) {
        this.stEntry = stEntry;
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
        return stEntry.getType();
    }

    public ArrayList<SemanticError> checkEffects(Environment env, Effect statusNew, Effect statusOld) {
        ArrayList<SemanticError> err = new ArrayList<>();
        Environment newEnv = new Environment();

        // dummy environment for status change of the id
        newEnv.newScope();
        newEnv.addDeclarationSafe(id, stEntry.getType(), statusNew);

        // calculate seq between old env and dummy env
        // replace old env with seq env
        env.replace(env.seq(newEnv));

        // check if new status of the var is an error
        stEntry = env.safeLookup(id);
        if (stEntry.getStatus().equals(new Effect(Effect.ERROR))) {
            err.add(new SemanticError("Error: variable: "+ id + " previously was on "+ statusOld + " status, and now is of " + statusNew + " status.\n"));
        }

        return err;
    }

}
