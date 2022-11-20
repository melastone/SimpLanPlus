package it.ghellimanca.semanticanalysis;

import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an entry of the symbol table.
 *
 */
public class STEntry {

    private TypeNode type;

    private final int nestingLevel;

    private final int offset;

    private Effect varStatus;

    private List<List<Effect>> funStatus;


    /**
     * Constructor for {@code STEntry}
     *
     * @param type          a TypeNode
     * @param nestingLevel  a positive integer
     * @param offset        a positive integer
     */
    public STEntry(TypeNode type, int nestingLevel, int offset) {
        this.type = type;
        this.nestingLevel = nestingLevel;
        this.offset = offset;
        this.varStatus = new Effect();
        this.funStatus = new ArrayList<>();
    }

    /**
     * Constructor for {@code STEntry}
     *
     * @param type          a TypeNode
     * @param nestingLevel  a positive integer
     * @param offset        a positive integer
     * @param status        instance of Effect class
     */
    public STEntry(TypeNode type, int nestingLevel, int offset, Effect status) {
        this.type = type;
        this.nestingLevel = nestingLevel;
        this.offset = offset;
        this.varStatus = status;
        this.funStatus = new ArrayList<>();
    }

    /**
     * Copy constructor for {@code STEntry}
     *
     * @param e STEntry to be copied
     */
    public STEntry(STEntry e){
        this(e.type, e.nestingLevel, e.offset, e.varStatus);
    }



    public TypeNode getType() {
        return type;
    }

    public void setType(TypeNode type) {
        this.type = type;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public int getOffset() {
        return offset;
    }

    public Effect getVarStatus() {
        return varStatus;
    }

    public void setVarStatus(Effect status) {
        this.varStatus = status;
    }

    public List<List<Effect>> getFunStatus() {
        return this.funStatus;
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

