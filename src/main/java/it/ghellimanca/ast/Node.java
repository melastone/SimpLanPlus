package it.ghellimanca.ast;

import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.*;

import java.util.ArrayList;

/**
 * A generic node of the AST
 */
public interface Node {

    /**
     * Returns a text representation of the AST node.
     *
     * @param indent: characters to prepend to the string which represents the current node
     * @return the current node string representation
     */
    String toPrint(String indent);

    /**
     * Check for semantic and effect analysis errors in the node.
     * */
    ArrayList<SemanticError> checkSemantics(Environment env) throws MissingInitializationException;


    /**
     * Type checking.
     * */
    TypeNode typeCheck() throws TypeCheckingException;

}
