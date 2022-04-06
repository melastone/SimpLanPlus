package it.ghellimanca.ast.exp;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;

import java.util.ArrayList;

/**
 * Represents an expression node in the AST that is the value returned by a function.
 */
public class CallExpNode extends ExpNode {
    //CallNode call;

    /*
    public CallExpNode(CallNode call) {
        this.call = call;
    }*/

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }

}
