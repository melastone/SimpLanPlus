package it.ghellimanca.ast.statement;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;

import java.util.ArrayList;

/**
 * Node of the AST for a return statement
 *
 * A return statement has the form:
 * 'return' (exp)?
 *
 */
public class ReturnNode extends StatementNode {

    final private ExpNode exp;


    public ReturnNode() {
        this.exp = null;
    }

    public ReturnNode(ExpNode exp) {
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "RET";
        if (this.exp != null) {
            res += exp.toPrint(indent + "\t");
        }
        return res;
    }

    @Override
    public String toString() { return toPrint("");}

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
