package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.type.IntTypeNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.errors.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an negated expression '-' exp node in the AST.
 *
 */
public class NegExpNode extends ExpNode {

    final ExpNode exp;


    public NegExpNode(ExpNode exp) {
        this.exp = exp;
    }



    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "NEG_EXP" + exp.toPrint(indent + "\t");
    }


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        return exp.checkSemantics(env);
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        // checking that the expression returns an int to be negated
        if (!(exp.typeCheck() instanceof IntTypeNode))
            throw new TypeCheckingException("Expression must be of type int to be negated.");

        return new IntTypeNode();
    }


    @Override
    public String codeGeneration() {
        return exp.codeGeneration() + "multi $a0 $a0 -1" + "\n";
    }


    @Override
    public List<IdNode> variables() {
        return exp.variables();
    }
}
