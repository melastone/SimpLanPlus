package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;

import java.util.ArrayList;

/**
 * A generic node of the AST
 */
public interface Node {

    /**
     * Check for errors in the node.
     * */
    ArrayList<SemanticError> checkSemantics(Environment env);

    /**
     * Type checking.
     * */
    Node typeCheck();

    /**
     * Check effects analysis errors.
     */
}
