package it.ghellimanca.ast.type;

import it.ghellimanca.ast.Node;;

/**
 * A generic type node of the AST.
 */

public abstract class TypeNode implements Node {

    @Override
    public String toString() {
        return toPrint("");
    }

    // this equal will be used to check the equality of two types in type checking functions
    @Override
    public boolean equals(Object obj) {
        return obj != null && this.getClass().equals(obj.getClass());
    }
}
