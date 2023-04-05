package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.type.BoolTypeNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.errors.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a boolean expression node in the AST.
 *
 */
public class BoolExpNode extends ExpNode {

    final boolean bool;


    public BoolExpNode(boolean bool) {
        this.bool = bool;
    }



    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "BOOL_EXP: " + bool;
    }


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        return new ArrayList<>();
    }


    @Override
    public TypeNode typeCheck() {
        return new BoolTypeNode();
    }


    @Override
    public String codeGeneration() {
        int asInt = bool ? 1 : 0;
        return "li $a0" + asInt + "\n";
    }


    @Override
    public List<IdNode> variables() {
        return new ArrayList<>();
    }
}
