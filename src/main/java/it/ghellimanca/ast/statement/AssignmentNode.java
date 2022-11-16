package it.ghellimanca.ast.statement;


import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.type.VoidTypeNode;
import it.ghellimanca.semanticanalysis.Effect;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

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
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> err = new ArrayList<>();

        // setting the new and old status of the id in question
        Effect newStat = new Effect(Effect.INIT);
        Effect oldStat;

        // first checking the semantic errors
        err.addAll(exp.checkSemantics(env));
        err.addAll(id.checkSemantics(env));

        // setting the old status of the id in question
        oldStat = env.safeLookup(id.getId()).getStatus();

        // checking if there are errors from the setting of the new status
        err.addAll(id.checkEffects(env, newStat, oldStat));

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


