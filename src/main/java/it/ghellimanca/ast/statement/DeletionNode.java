package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.type.PointerTypeNode;
import it.ghellimanca.ast.type.VoidTypeNode;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

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
    public TypeNode typeCheck() throws TypeCheckingException {
        TypeNode idType = id.typeCheck();

        // checking that the id is a pointer to be deleted
        if(!(idType instanceof PointerTypeNode))
            throw new TypeCheckingException("Id " + id + " must be a pointer to be deleted.");

        return new VoidTypeNode();
    }
}
