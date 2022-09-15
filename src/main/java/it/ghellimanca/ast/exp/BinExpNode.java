package it.ghellimanca.ast.exp;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.type.BoolTypeNode;
import it.ghellimanca.ast.type.IntTypeNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

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
    public TypeNode typeCheck() throws TypeCheckingException {
        TypeNode left = leftExp.typeCheck();
        TypeNode right = rightExp.typeCheck();

        // checking that the expression have the same type
        if (!left.equals(right)) {
            throw new TypeCheckingException("Left expression: " + leftExp.toPrint("\t") + " \nand right expression: " + rightExp.toPrint("\t") + " \nhave incompatible types.");
        }

        switch (operator) {
            // operators for integer type exps
            case "*":
            case "/":
            case "+":
            case "-":
                // i only check the left exp's type just because i already checked that it is the same as the right one
                if (!(left instanceof IntTypeNode)) {
                    throw new TypeCheckingException("The operator: " + operator + " requires integer expressions.");
                }
                return new IntTypeNode();

            case "<":
            case "<=":
            case ">":
            case ">=":
                if (!(left instanceof IntTypeNode)) {
                    throw new TypeCheckingException("The operator: " + operator + " requires integer expressions.");
                }
                return new BoolTypeNode();

            // operators for exps of the same type that always return a bool
            case "==":
            case "!=":
                return new BoolTypeNode();

            // operators for boolean type exps
            case "&&":
            case "||":
                if (!(left instanceof BoolTypeNode)) {
                    throw new TypeCheckingException("The operator: " + operator + " requires boolean expressions.");
                }
                return new BoolTypeNode();

            // this is needed just because we don't have a structure that limits the string to just all the different operators
            default:
                return null;
        }
    }
}
