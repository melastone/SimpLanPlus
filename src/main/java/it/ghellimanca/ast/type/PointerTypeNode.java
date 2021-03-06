package it.ghellimanca.ast.type;

/**
 * Represents a pointer type node in the AST.
 */
public class PointerTypeNode extends TypeNode {

    final TypeNode pointedType;

    public PointerTypeNode(TypeNode pointedType) {
        this.pointedType = pointedType;
    }

    //TODO (pointertypenode) capire se inserire checksemantics, typecheck, ecc
    // perchè per il resto non fa nulla


    public TypeNode getPointedType() {
        return pointedType;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "TYPE: " + "^" + pointedType.toPrint(indent + "\t");
    }
}
