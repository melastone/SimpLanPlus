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

        // setting the new status of the id in question
        Effect newStat = new Effect(Effect.USED);

        // dummy environment for status change of the id
        Environment newEnv = new Environment();
        newEnv.newScope();
        newEnv.addDeclarationSafe(id.getId(), id.getStEntry().getType(), newStat);

        // replace old env with seq of old env and dummy env
        env.replace(env.seq(newEnv));

        // set new status in the entry inside idnode
        id.setStEntry(env.safeLookup(id.getId()));

        return err;
    }

    @Override
    public TypeNode typeCheck() {
        return id.typeCheck();
    }
}
