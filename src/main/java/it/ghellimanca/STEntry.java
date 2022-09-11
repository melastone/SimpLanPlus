package it.ghellimanca;

import it.ghellimanca.ast.type.TypeNode;

// represents an entry of the symbol table
 public class STEntry {
    private final int nestingLevel;

    private TypeNode type;

    private final int offset;

    public STEntry(int nestingLevel, int offset) {
        this.nestingLevel = nestingLevel;
        this.offset = offset;
    }

    public STEntry(int nestingLevel, TypeNode type, int offset) {
        this(nestingLevel, offset);
        this.type = type;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public TypeNode getType() {
        return type;
    }

    public void setType(TypeNode type) {
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

}

