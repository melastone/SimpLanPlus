package it.ghellimanca.ast.exp;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.LhsNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;

/**
 * Represents the result of dereferencing something as a node in the AST.
 */
public class DerExpNode extends ExpNode {

    final LhsNode lhs;


    public DerExpNode(LhsNode lhs) {
        this.lhs = lhs;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "DER_EXP" + lhs.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return lhs.checkSemantics(env);
    }

    @Override
    public TypeNode typeCheck() {
        return lhs.typeCheck();
    }
}
