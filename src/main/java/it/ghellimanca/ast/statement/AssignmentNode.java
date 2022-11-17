package it.ghellimanca.ast.statement;


import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.type.VoidTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;

/**
 * Node of the AST for an assignment statement
 *
 * An assignment has the form:
 * id '=' exp
 *
 */
public class AssignmentNode extends StatementNode {

    final private IdNode id;
    final private ExpNode exp;


    public AssignmentNode(IdNode id, ExpNode exp) {
        this.id = id;
        this.exp = exp;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "ASSIGNMENT" + id.toPrint(indent + "\t") + exp.toPrint(indent + "\t");
    }

    @Override
    public String toString() { return toPrint("");}


    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) throws MissingInitializationException {
        ArrayList<SemanticError> err = new ArrayList<>();
        Environment newEnv = new Environment();

        // setting the new status of the id in question
        Effect newStat = new Effect(Effect.INIT);

        // first checking the semantic errors
        err.addAll(exp.checkSemantics(env));
        err.addAll(id.checkSemantics(env));

        // dummy environment for status change of the id
        newEnv.newScope();
        newEnv.addDeclarationSafe(id.getId(), id.getStEntry().getType(), newStat);

        // replace old env with seq of old env and dummy env
        env.replace(env.seq(newEnv));

        // set new status in the entry inside idnode
        id.setStEntry(env.safeLookup(id.getId()));

        return err;
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        TypeNode idType = id.typeCheck();
        TypeNode expType = exp.typeCheck();

        if (!idType.equals(expType)) {
            throw new TypeCheckingException("Can't assign expression of type " + expType + " to id of type " + idType);
        }

        return new VoidTypeNode();
    }
}


