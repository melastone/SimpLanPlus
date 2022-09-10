package it.ghellimanca.ast.statement;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;

import java.util.ArrayList;

/**
 * Node of the AST for a print statement
 *
 * A print statement has the form:
 * 'print' exp
 *
 */
public class PrintNode extends StatementNode {

    final private ExpNode exp;


    public PrintNode(ExpNode exp) {
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {

        return "\n" + indent + "PRINT" + exp.toPrint(indent + "\t");
    }

    @Override
    public String toString() { return toPrint("");}

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return exp.checkSemantics(env);

    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
