package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;

import java.util.List;


/**
 * Node of the AST for a generic statement
 *
 *
 */
public abstract class StatementNode implements Node {

    String funId;   // used by statements that belongs to the same function in order to have a reference to it


    public String toString() {
        return toPrint("");
    }

    public void setFunId(String funId) {
        this.funId = funId;
    }

    /**
     * Gets all the variables used as parameters or inside them.
     *
     * @return  list of IdNodes, each for one variable
     */
    public abstract List<IdNode> variables();

    /**
     * Gets all the variables that are declared inside the statement.
     *
     * @return  list of IdNodes, each for one variable.
     */
    public abstract List<IdNode> getVarDeclarations();


}
