package it.ghellimanca.semanticanalysis;

import java.util.Objects;

public class Effect {

    private static final int DECLARED_ = 0;

    private static final int INITIALIZED_ = 1;

    private static final int USED_ = 2;

    private static final int ERROR_ = 3;
    private final int status;

    public Effect(int status) {
        this.status = status;
    }

    // MAX
    public Effect max(Effect e1, Effect e2) {
        return new Effect(Math.max(e1.status, e2.status));
    }

    // SEQ
    public Effect seq(Effect e1, Effect e2) {
        Effect max = max(e1, e2);
        if (((e1.status != DECLARED_) && (e2.status != USED_)) && (max.status <= USED_)) {
            return max;
        }
        else
            return new Effect(ERROR_);
    }

    // BIN
    public Effect bin(Effect e1, Effect e2) {
        if (e1.status == ERROR_ || e2.status == ERROR_) {
            return new Effect(ERROR_);
        } else if(e1.status == DECLARED_ || e2.status == DECLARED_) {
            return new Effect(DECLARED_);
        } else {
            return max(e1, e2);
        }
    }

    // PAR
    public Effect par(Effect e1, Effect e2) {
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
