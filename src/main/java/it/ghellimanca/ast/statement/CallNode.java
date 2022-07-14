package it.ghellimanca.ast.statement;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Node of the AST for a call statement
 *
 * A call statement has the form:
 * ID '(' (exp(',' exp)*)? ')'
 *
 */
public class CallNode implements Node {

    final private IdNode id;
    final private List<ExpNode> params;


    /*
    Constructor for a nullary function (i.e. takes no args) call
     */
    public CallNode(IdNode id) {
        this.id = id;
        this.params = null;
    }

    /*
    Constuctor for a function call with parameters
     */
    public CallNode(IdNode id, List<ExpNode> params) {
        this.id = id;
        this.params = params;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }
}
