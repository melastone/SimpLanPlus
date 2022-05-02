package it.ghellimanca.ast.statement;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
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
public class AssignmentNode implements Node {

    final private LhsNode lhs;
    final private ExpNode exp;


    public AssignmentNode(LhsNode lhs, ExpNode exp) {
        this.lhs = lhs;
        this.exp = exp;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
