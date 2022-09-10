package it.ghellimanca.ast.exp;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.statement.CallNode;

import java.util.ArrayList;

/**
 * Represents a function call expression ID '(' (exp(',' exp)*)? ')' node in the AST.
 */
public class CallExpNode extends ExpNode {

    CallNode call;


    public CallExpNode(CallNode call) {
        this.call = call;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "CALL_EXP" + call.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return call.checkSemantics(env);
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
