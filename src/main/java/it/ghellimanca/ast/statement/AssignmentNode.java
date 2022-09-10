package it.ghellimanca.ast.statement;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.LhsNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;

import java.util.ArrayList;

/**
 * Node of the AST for an assignment statement
 *
 * An assignment has the form:
 * lhs '=' exp
 *
 */
public class AssignmentNode extends StatementNode {

    final private LhsNode lhs;
    final private ExpNode exp;


    public AssignmentNode(LhsNode lhs, ExpNode exp) {
        this.lhs = lhs;
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "ASSIGNMENT" + lhs.toPrint(indent + "\t") + exp.toPrint(indent + "\t");
    }

    @Override
    public String toString() { return toPrint("");}

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> err = new ArrayList<>();

        err.addAll(lhs.checkSemantics(env));
        err.addAll(exp.checkSemantics(env));

        return err;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
