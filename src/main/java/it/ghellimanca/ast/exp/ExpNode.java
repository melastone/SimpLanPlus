package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;

import java.util.List;

/**
 * Represents a generic expression node in the AST
 */
public abstract class ExpNode implements Node {

    public String toString() {
        return toPrint("");
    }

    /**
     * Gets all the variables used inside the expression.
     *
     * @return  list of IdNodes, each for one variable
     */
    public abstract List<IdNode> variables();
}
