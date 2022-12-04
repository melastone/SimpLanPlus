package it.ghellimanca.ast.statement;


import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.type.VoidTypeNode;
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

        err.addAll(id.checkSemantics(env));
        err.addAll(exp.checkSemantics(env));

        id.getStEntry().setInitAfterDec(true);

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


