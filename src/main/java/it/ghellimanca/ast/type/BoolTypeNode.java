package it.ghellimanca.ast.type;


import it.ghellimanca.semanticanalysis.*;

import java.util.ArrayList;

/**
 * Represents a boolean type node in the AST.
 */
public class BoolTypeNode extends TypeNode {

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "TYPE: " + "bool";
    }

    @Override
    public String toString() {
        return "bool";
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

