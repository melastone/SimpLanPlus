package it.ghellimanca.semanticanalysis;


/**
 * Represents an effect for a variable.
 * It is used for Effect Analysis.
 */
public class Effect {

    public static final int DECLARED = 0;  //  ⊥
    public static final int INIT = 1;
    public static final int USED = 2;
    public static final int ERROR = 3;     // ⊤

    private final int status;


    public Effect() {
        this(DECLARED);
    }

    public Effect(int status) {
        this.status = status;
    }


    public int getStatus() {
        return status;
    }


    /**
     * Implements the max binary operator
     *
     * @param e1 first effect
     * @param e2 second effect
     * @return the highest effect between e1 and e2
     */
    public static Effect max(Effect e1, Effect e2) {
        return new Effect(Math.max(e1.status, e2.status));
    }

    /**
     * Implements the sequential binary operator
     *
     * SEQ | ⊥ | 1 | 2 | ⊤
     *   ⊥ | ⊥ | 1 | T | ⊤
     *   1 | 1 | 1 | 2 | ⊤
     *   2 | 2 | 2 | 2 | ⊤
     *   ⊤ | ⊤ | ⊤ | ⊤ | ⊤
     *
     * @param e1 previous effect
     * @param e2 new effect to assign
     * @return the result effect of the update
     */
    public static Effect seq(Effect e1, Effect e2) {
        Effect max = max(e1, e2);
        if (((e1.status != DECLARED) && (e2.status != USED)) && (max.status <= USED)) {
            return max;
        }
        else
            return new Effect(ERROR);
    }

    /**
     * Implements a binary operator that propagates the most critical effect.
     * It is used for conditional satements.
     *
     * BIN | ⊥ | 1 | 2 | ⊤
     *   ⊥ | ⊥ | ⊥ | ⊥ | ⊤
     *   1 | ⊥ | 1 | 2 | ⊤
     *   2 | ⊥ | 2 | 2 | ⊤
     *   ⊤ | ⊤ | ⊤ | ⊤ | ⊤
     *
     * @param e1 first effect
     * @param e2 second effect
     * @return the effect that is most critical, following the truth table
     */
    public static Effect bin(Effect e1, Effect e2) {
        if (e1.status == ERROR || e2.status == ERROR) {
            return new Effect(ERROR);
        } else if(e1.status == DECLARED || e2.status == DECLARED) {
            return new Effect(DECLARED);
        } else {
            return max(e1, e2);
        }
    }

    /**
     * Implements the ???? par operator
     * todo: scrivere la definizione di questa funzione, che ancora non ho capito ehehehe
     *
     * @param e1
     * @param e2
     * @return
     */
    public static Effect par(Effect e1, Effect e2) {
        return max(e1, e2);
    }

    /* OTHER UTILS */

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Effect effect = (Effect) o;
        return status == effect.status;
    }

}
