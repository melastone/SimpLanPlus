package it.ghellimanca.ast.type;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

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
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        return null;
    }

    @Override
    public String toString() {
        return "int";
    }
}
