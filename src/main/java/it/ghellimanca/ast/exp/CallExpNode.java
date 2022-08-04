package it.ghellimanca.ast.exp;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.statement.CallNode;

import java.util.ArrayList;

/**
 * Represents an expression node in the AST that is the value returned by a function.
 */
public class CallExpNode extends ExpNode {

    CallNode call;

    public CallExpNode(CallNode call) {
        this.call = call;
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
        return "\n" + indent + "CALL_EXP" + call.toPrint(indent + "\t");
    }
}
