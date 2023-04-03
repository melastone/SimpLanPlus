package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;

import java.util.List;

/**
 * Node of the AST for a generic statement
 *
 */
public abstract class StatementNode implements Node {

    String funId;   // used by statements that belongs to the same function in order to have a reference to it


    public void setFunId(String funId) {
        this.funId = funId;
    }

    public String getFunId() {
        return funId;
    }

    public abstract List<IdNode> variables();

    public String toString() {
        return toPrint("");
    }
}
