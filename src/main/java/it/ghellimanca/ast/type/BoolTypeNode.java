package it.ghellimanca.ast.type;

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
}

