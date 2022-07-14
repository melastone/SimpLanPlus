package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.Node;

/**
 * Represents a generic expression node in the AST
 */
public abstract class ExpNode implements Node {

    public String toString() {
        return toPrint("");
    }
}
