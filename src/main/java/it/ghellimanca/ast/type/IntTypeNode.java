package it.ghellimanca.ast.type;


import it.ghellimanca.semanticanalysis.*;

import java.util.ArrayList;

/**
 * Represents a integer type node in the AST.
 */
public class IntTypeNode extends TypeNode {

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "TYPE: " + "int";
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        return new ArrayList<>();
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

}
