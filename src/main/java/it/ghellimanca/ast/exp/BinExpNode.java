package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.type.BoolTypeNode;
import it.ghellimanca.ast.type.IntTypeNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a binary expression left=exp op right=exp node in the AST.
 *
 * todo: sostituire le LABEL esplicite con lil gestore delle label, dopo averne deciso
 */
public class BinExpNode extends ExpNode {

    final ExpNode leftExp;
    final String operator;
    final ExpNode rightExp;



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
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {

        ArrayList<SemanticWarning> err = new ArrayList<>();

        err.addAll(leftExp.checkSemantics(env));
        err.addAll(rightExp.checkSemantics(env));

        return err;
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        TypeNode left = leftExp.typeCheck();
        TypeNode right = rightExp.typeCheck();

        // checking that the expression have the same type
        if (!left.equals(right)) {
            throw new TypeCheckingException("Left expression: " + leftExp.toPrint("\t") + " of type " + left + " \nand right expression: " + rightExp.toPrint("\t") + " of type " + right + " \nhave incompatible types.");
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

    @Override
    public String codeGeneration() {
        StringBuilder res = new StringBuilder();

        res.append(leftExp.codeGeneration());
        res.append("push $a0\n");
        res.append(rightExp.codeGeneration());
        res.append("lw $t1 0($sp)\n");
        res.append("pop\n");

        switch (operator) {
            case "*":
                res.append("mult $a0 $t1 $a0\n");
                break;
            case "/":
                res.append("div $a0 $t1 $a0\n");
                break;
            case "+":
                res.append("add $a0 $t1 $a0\n");
                break;
            case "-":
                res.append("sub $a0 $t1 $a0\n");
                break;
            case "<":
                String lessEqTrueBrLabel = "LESS_EQ_TRUE_BRANCH";
                String endLessLabel = "END LESS";
                String lessLeqTrueBrLabel = "LESS_LEQ_TRUE_BRANCH";

                res.append("beq $a0 $t1 ").append(lessEqTrueBrLabel).append("\n");

                /* EQUAL IS FALSE */
                res.append("bleq $a0 $t1 ").append(lessLeqTrueBrLabel).append("\n");

                // LESS IS FALSE
                res.append("li $a0 0\n");
                res.append("b ").append(endLessLabel).append("\n");

                // LESS IS TRUE
                res.append(lessLeqTrueBrLabel).append(":\n");
                res.append("li $a0 1\n");
                res.append("b ").append(endLessLabel).append("\n");

                // EQUAL IS TRUE, LESS IS FALSE
                res.append(lessEqTrueBrLabel).append(":\n");
                res.append("li $a0 0\n");

                res.append(endLessLabel).append(":\n");
                break;
            case "<=":
                String leqTrueBrLabel = "LEQ_TRUE_BRANCH";
                String endLeqLabel = "END LEQ";

                res.append("bleq $a0 $t1 ").append(leqTrueBrLabel).append("\n");

                // LEQ_FALSE_BRANCH:
                res.append("li $a0 0\n");
                res.append("b ").append(endLeqLabel).append("\n");

                res.append(leqTrueBrLabel).append(":\n");
                res.append("li $a0 1\n");

                res.append(endLeqLabel).append(":\n");
                break;
            case ">":
                String grEqTrueBrLabel = "GR_EQ_TRUE_BRANCH";
                String endGrLabel = "END GR";

                res.append("bleq $a0 $t1 ").append(grEqTrueBrLabel).append("\n");

                // BLEQ IS FALSE, GR IS TRUE
                res.append("li $a0 1\n");
                res.append("b ").append(endGrLabel).append("\n");

                // BLEQ IS TRUE, GR IS FALSE
                res.append(grEqTrueBrLabel).append(":\n");
                res.append("li $a0 0\n");

                res.append(endGrLabel).append(":\n");
                break;
            case ">=":
                String geqTrueBrLabel = "GEQ_TRUE_BRANCH";
                String geqFalseBrLabel = "GEQ_FALSE_BRANCH";
                String endGeqLabel = "END GEQ";

                res.append("beq $a0 $t1 ").append(geqTrueBrLabel).append("\n");

                /* EQUAL IS FALSE */
                res.append("bleq $a0 $t1 ").append(geqFalseBrLabel).append("\n");

                // BLEQ IS FALSE
                res.append("b ").append(geqTrueBrLabel).append("\n");

                // GEQ IS FALSE
                res.append(geqFalseBrLabel).append(":\n");
                res.append("li $a0 0\n");
                res.append("b ").append(endGeqLabel).append("\n");

                /* GEQ IS TRUE */
                res.append(geqTrueBrLabel).append(":\n");
                res.append("li $a0 1\n");

                res.append(endGeqLabel).append(":\n");
                break;
            case "==":
                String eqTrueBrLabel = "EQ_TRUE_BRANCH";
                String endEqLabel = "END EQ";

                res.append("beq $a0 $t1 ").append(eqTrueBrLabel).append("\n");

                // EQ_FALSE_BRANCH:
                res.append("li $a0 0\n");
                res.append("b ").append(endEqLabel).append("\n");

                res.append(eqTrueBrLabel).append(":\n");
                res.append("li $a0 1\n");

                res.append(endEqLabel).append(":\n");
                break;
            case "!=":
                String uneqTrueBrLabel = "UNEQ_TRUE_BRANCH";
                String endUneqLabel = "END UNEQ";

                res.append("beq $a0 $t1 ").append(uneqTrueBrLabel).append("\n");

                // UNEQ_FALSE_BRANCH:
                res.append("li $a0 1\n");
                res.append("b ").append(endUneqLabel).append("\n");

                res.append(uneqTrueBrLabel).append(":\n");
                res.append("li $a0 0\n");

                res.append(endUneqLabel).append(":\n");
                break;
            case "&&":
                res.append("and $a0 $t1 $a0\n");
                break;
            case "||":
                res.append("or $a0 $t1 $a0\n");
                break;
        }

        return res.toString();
    }


    @Override
    public List<IdNode> variables() {
        List<IdNode> variables = new ArrayList<>();

        variables.addAll(leftExp.variables());
        variables.addAll(rightExp.variables());

        return variables;
    }
}
