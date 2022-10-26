package it.ghellimanca.semanticanalysis;

import it.ghellimanca.ast.type.TypeNode;

/**
 * Represents an entry of the symbol table.
 *
 */
public class STEntry {

    private TypeNode type;

    private final int nestingLevel;

    private final int offset;

    private Effect status;


    /**
     * Constructor for {@code STEntry}
     *
     * @param nestingLevel  a positive integer
     * @param type          a TypeNode
     * @param offset        a positive integer
     */
    public STEntry(TypeNode type, int nestingLevel, int offset, Effect status) {
        this.type = type;
        this.nestingLevel = nestingLevel;
        this.offset = offset;
        this.status = status;
    }

    public STEntry(TypeNode type, int nestingLevel, int offset) {
        this.type = type;
        this.nestingLevel = nestingLevel;
        this.offset = offset;
    }

    /**
     * Copy constructor for {@code STEntry}
     *
     * @param e STEntry to be copied
     */
    public STEntry(STEntry e){
        this(e.type, e.nestingLevel, e.offset, e.status);
    }


    public int getNestingLevel() {
        return nestingLevel;
    }

    public Effect getStatus() {
        return status;
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
    public void setStatus(Effect status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "STentry{" +
                "nestingLevel=" + nestingLevel +
                ", type=" + type +
                ", offset=" + offset +
                '}';
    }
}

