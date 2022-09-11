package it.ghellimanca.ast.statement;


import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.LhsNode;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;

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

    // check if the id of the lhs that is being assigned has been declared in this level or below
    // if not: semantic error
    // if yes: check semantic of expression
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> err = new ArrayList<>();

        err.addAll(lhs.checkSemantics(env));
        err.addAll(exp.checkSemantics(env));

        return err;
    }

    @Override
    public TypeNode typeCheck() {
        return null;
    }
}
