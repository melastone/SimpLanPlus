package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;

/**
 * Represents the result of dereferencing something as a node in the AST.
 */
public class DerExpNode extends ExpNode {

    final IdNode id;

    public DerExpNode(IdNode id) {
        this.id = id;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "DER_EXP" + id.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        // first checking the semantic errors
        ArrayList<SemanticError> err = id.checkSemantics(env);

        // setting the new and old status of the id in question
        Effect oldStat = env.safeLookup(id.getId()).getStatus();
        Effect newStat = new Effect(Effect.USED);

        // checking if there are errors from the setting of the new status
        err.addAll(id.checkEffects(env, newStat, oldStat));

        return err;
    }

    @Override
    public TypeNode typeCheck() {
        return id.typeCheck();
    }
}
