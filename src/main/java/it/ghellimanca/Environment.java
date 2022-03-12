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

import it.ghellimanca.STEntry;

public class Environment {

    /*
     * Modifiers:
     * final cause it's not supposed to be overridden
     * private because outside of this class the code interacts
     * with the table via Environment class methods.
     *
     */
    final private List<Map<String,STEntry>> symbolTable ;


    public List<Map<String,STEntry>> newScope()
    // extends the symbolTable with a new empty scope

    public List<Map<String,STEntry>> newScope(Map<String,STEntry> s)
    // extends the symbolTable with an initialized scope s

    public List<Map<String,STEntry>> addDecl(String id, Type t)
    // if there is no clash of names, adds id ⟼ t to st
    // if it find a collision needs to throw an exception

    Type lookup(String id)
    // looks for the type of id in st, if any

    public List<Map<String,STEntry>> exitScope()
    // exits the current scope

}
