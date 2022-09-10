package it.ghellimanca.ast.type;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;

import java.util.ArrayList;

/**
 * Represents a boolean type node in the AST.
 */
public class BoolTypeNode extends TypeNode {

    //TODO (booltypenode) capire se inserire checksemantics, typecheck, ecc
    // perchè per il resto non fa nulla

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
}

