package it.ghellimanca;

import it.ghellimanca.ast.type.TypeNode;

// represents an entry of the symbol table
public class STentry {

    //nesting level
    private final int nestingLevel;
    //type
    private TypeNode type;

    private final int offset;

    public STentry(int nestingLevel, int offset) {
        this.nestingLevel = nestingLevel;
        this.offset = offset;
    }

    public STentry(int nestingLevel, TypeNode type, int offset) {
        this(nestingLevel, offset);
        this.type = type;

        //TODO gestire in modo particolare tipo FunTypeNode
        //type instanceof FunTypeNode
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

    //TODO implementare equals
}

