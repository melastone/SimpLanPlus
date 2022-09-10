package it.ghellimanca.ast.exp;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.Node;

import java.util.ArrayList;

/**
 * Represents a binary expression left=exp op right=exp node in the AST.
 */
public class BinExpNode extends ExpNode {

    final ExpNode leftExp;
    final String operator;
    final ExpNode rightExp;

    //TODO: context contiene anche i seguenti attributi, controllare in futuro se servono

    //final List<ExpNode> expList;
    //final ExpNode exp;

    public BinExpNode(ExpNode leftExp, String operator, ExpNode rightExp) {
        this.leftExp = leftExp;
        this.operator = operator;
        this.rightExp = rightExp;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "BIN_EXP" + leftExp.toPrint(indent + "\t")
                    + "\n" + indent + "\t" + "operator: " + operator
                    + rightExp.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> err = new ArrayList<>();

        if (this.leftExp != null) {
            err.addAll(leftExp.checkSemantics(env));
        }
        if(this.rightExp != null) {
            err.addAll(rightExp.checkSemantics(env));
        }

        return err;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
