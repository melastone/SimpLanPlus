package it.ghellimanca.ast.type;

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
}
