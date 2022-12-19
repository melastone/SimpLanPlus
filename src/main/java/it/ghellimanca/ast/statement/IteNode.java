package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.type.BoolTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;


/**
 * Node of the AST for an iteration statement
 *
 * An ite statement has the form:
 * 'if' '(' exp ')' statement ('else' statement)?
 *
 */
public class IteNode extends StatementNode {

    final private ExpNode exp;
    final private StatementNode stm1;
    final private StatementNode stm2;



    public IteNode(ExpNode exp, StatementNode stm1, StatementNode stm2) {
        this.exp = exp;
        this.stm1 = stm1;
        this.stm2 = stm2;
    }

    public IteNode(ExpNode exp, StatementNode stm1) {
        this.exp = exp;
        this.stm1 = stm1;
        this.stm2 = null;
    }



    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "ITE" + exp.toPrint(indent + "\t") + stm1.toPrint(indent + "\t");

        if (this.stm2 != null) {
            res += stm2.toPrint(indent + "\t");
        }
        return res;
    }


    @Override
    public String toString() { return toPrint("");}


    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) throws MissingInitializationException, ParametersCountException {
        ArrayList<SemanticError> err = new ArrayList<>();

        err.addAll(exp.checkSemantics(env));


        if(this.stm2 == null) {     // else branch empty
            err.addAll(stm1.checkSemantics(env));
        } else {                    // else branch not empty
            Environment thenBranchEnv = new Environment(env);   // copy of env for then branch eval; it will change with results of its eval
            err.addAll(stm1.checkSemantics(thenBranchEnv));

            Environment elseBranchEnv = new Environment(env);   // copy of env for else branch eval
            err.addAll(stm2.checkSemantics(elseBranchEnv));

            // bin fra thenbranch e elsebranch
            // replace env con risultato bin
            thenBranchEnv.bin(elseBranchEnv);
            env.replace(thenBranchEnv);
        }

        return err;

    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        TypeNode condType = exp.typeCheck();
        TypeNode stm1Type = stm1.typeCheck();

        // checking that the condition is a boolean expression
        if(!(condType instanceof BoolTypeNode))
            throw new TypeCheckingException("Condition of the if statement is not of type bool.");

        // checking that if there is an else branch it's the same type as the then branch
        if (this.stm2 != null && !(stm1Type.equals(stm2.typeCheck()))) {
            throw new TypeCheckingException("Then and else branch of the if statement have different types.");
        }

        // returning the branches type
        return stm1Type;
    }
}
