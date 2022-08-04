package it.ghellimanca.semanticanalysis;

import it.ghellimanca.ast.type.TypeNode;

/**
 * Represents an entry of the symbol table.
 *
 */
public class STEntry {

    //nesting level
    private final int nestingLevel;
    //type
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

    @Override
    public String toString() {
        return "STentry{" +
                "nestingLevel=" + nestingLevel +
                ", type=" + type +
                ", offset=" + offset +
                '}';
    }

    //todo implementare equals
}

