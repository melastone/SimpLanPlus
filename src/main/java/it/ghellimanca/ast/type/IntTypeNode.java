package it.ghellimanca.ast.type;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;

import java.util.ArrayList;

/**
 * Represents a integer type node in the AST.
 */
public class IntTypeNode extends TypeNode {

    //TODO (inttypenode) capire se inserire checksemantics, typecheck, ecc
    // perchè per il resto non fa nulla


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "TYPE: " + "int";
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }
}
