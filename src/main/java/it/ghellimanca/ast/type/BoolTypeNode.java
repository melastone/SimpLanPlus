package it.ghellimanca.ast.type;


import it.ghellimanca.semanticanalysis.TypeCheckingException;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;

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
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        return null;
    }
}

