package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.statement.CallNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a function call expression ID '(' (exp(',' exp)*)? ')' node in the AST.
 *
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


    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersCountException {
        return call.checkSemantics(env);
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        return call.typeCheck();
    }

    @Override
    public List<IdNode> variables() {
        return call.variables();
    }
}
