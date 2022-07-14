package it.ghellimanca.ast.statement;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;

import java.util.ArrayList;

/**
 * Node of the AST for an iteration statement
 *
 * An ite statement has the form:
 * 'if' '(' exp ')' statement ('else' statement)?
 *
 */
public class IteNode implements Node {

    final private ExpNode exp;
    final private StatementNode stm1;
    final private StatementNode stm2;


    public IteNode(ExpNode exp, StatementNode stm1, StatementNode stm2) {
        this.exp = exp;
        this.stm1 = stm1;
        this.stm2 = stm2;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "If:\t" + exp.toPrint(indent) + "\nThen:" + stm1.toPrint(indent) + ;
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
