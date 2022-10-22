package it.ghellimanca.semanticanalysis;

/**
 * Represents an effect for a variable.
 * It is used for Effect Analysis.
 */
public class Effect {

    private static final int DECLARED = 0;  //  ⊥
    private static final int INIT = 1;
    private static final int USED = 2;
    private static final int ERROR = 3;     // ⊤

    private final int status;


    public Effect(int status) {
        this.status = status;
    }

    public Effect() {
        this(DECLARED);
    }

}
