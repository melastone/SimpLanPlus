package it.ghellimanca.ast.statement;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;

/**
 * Node of the AST for the deletion statement
 *
 * A deletion has the form:
 * 'delete' ID
 *
 */
public class DeletionNode extends StatementNode {

    final private IdNode id;


    public DeletionNode(IdNode id) {
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "DELETION" + id.toPrint(indent + "\t");
    }

    @Override
    public String toString() { return toPrint("");}

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return id.checkSemantics(env);
    }

    @Override
    public TypeNode typeCheck() {
        return null;
    }
}
