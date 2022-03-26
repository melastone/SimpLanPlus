package it.ghellimanca;

/*
 * This class represents the Symbol Table.
 *
 * It is implemented as a list of hashtables.
 *
 * The list is treated as a stack (methods use the pop/push operations).
 *
 */

import java.util.List;
import java.util.Map;

public class Environment {

    /*
     * Modifiers:
     * final cause it's not supposed to be overridden
     * private because outside of this class the code interacts
     * with the table via Environment class methods.
     *
     */
    final private List<Map<String, STentry>> symbolTable;

    public Environment(List<Map<String, STentry>> symbolTable) {
        this.symbolTable = symbolTable;
    }
}
