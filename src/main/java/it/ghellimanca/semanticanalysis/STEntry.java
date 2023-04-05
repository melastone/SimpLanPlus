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

    private boolean initAfterDec;

    private final List<List<Effect>> funStatus;

    private List<Boolean> initPars;



    /**
     * Constructor for {@code STEntry}.
     * Typically used by {@code Environment.addDeclaration}, which choose between declared and init statuses.
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
        this.initAfterDec = false;  // default, cause this constructor is used by DeclarationNodes
        this.funStatus = new ArrayList<>();
        this.initPars = new ArrayList<>();
    }


    /**
     * Constructor for {@code STEntry}.
     * This is the complete version, that takes as params all the STEntry fields.
     *
     * @param type          a TypeNode
     * @param nestingLevel  a positive integer
     * @param offset        a positive integer
     * @param status        instance of Effect class
     */
    public STEntry(TypeNode type, int nestingLevel, int offset, Effect status, boolean isInitAfterDec, List<List<Effect>> funStatus, List<Boolean> initPars) {
        this.type = type;
        this.nestingLevel = nestingLevel;
        this.offset = offset;
        this.varStatus = status;
        this.initAfterDec = isInitAfterDec;
        this.funStatus = funStatus;
        this.initPars = initPars;
    }


    /**
     * Copy constructor for {@code STEntry}
     *
     * @param e STEntry to be copied
     */
    public STEntry(STEntry e){
        this(e.type, e.nestingLevel, e.offset, e.varStatus, e.initAfterDec, e.funStatus, e.initPars);
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

    public void setInitAfterDec(boolean initAfterDec) {
        this.initAfterDec = initAfterDec;
    }

    public boolean isInitAfterDec() {
        return initAfterDec;
    }

    public List<List<Effect>> getFunStatus() {
        return this.funStatus;
    }

    public void setFunStatus(int index, List<Effect> statuses) {
        this.funStatus.add(index, statuses);
    }

    public List<Boolean> getInitPars() {
        return initPars;
    }

    public void setInitPars(List<Boolean> initPars) {
        this.initPars = initPars;
    }


    @Override
    public String toString() {

        return "STentry{" +
                "nestingLevel=" + nestingLevel +
                ",\n type=" + type +
                ",\n offset=" + offset +
                ",\n varStatus=" + varStatus +
                ",\n initAfterDec=" + initAfterDec +
                ",\n funStatus=" + funStatus +
                ",\n initPars=" + initPars +
                "}\n";
    }
}

