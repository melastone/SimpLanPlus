package it.ghellimanca.ast.type;

import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.semanticanalysis.errors.*;

import java.util.ArrayList;

/**
 * Wrapper for a type node that identifies a variable passed by reference.
 *
 * ex. var int x
 */
public class VarTypeNode extends TypeNode{

    final TypeNode type;



    public VarTypeNode(TypeNode type) {
        this.type = type;
    }



    public TypeNode getType() {
        return type;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "TYPE: " + "&" + type;
    }


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        return new ArrayList<>();
    }


    @Override
    public TypeNode typeCheck() { return null; }


    @Override
    public String codeGeneration() {
        return "";
    }
}
