package it.ghellimanca;

/**
 * This class represents the Symbol Table.
 * It is implemented as a list of hashtables.
 */

import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.MissingDeclarationException;

import java.util.HashMap;
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
    private List<Map<String, STEntry>> symbolTable;

    private int nestingLevel;

    private int offset;


    /**
     * Constructor for {@code Environment}
     *
     * @param symTable     a list of hashmaps (that can be empty)
     * @param nestingLevel a positive integer
     */
    public Environment(List<Map<String, STEntry>> symTable, int nestingLevel) {
       this.symbolTable = symTable;
       this.nestingLevel = nestingLevel;
    }

    public List<Map<String, STEntry>> getSymbolTable() {
        return symbolTable;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public int getOffset() {
        return offset;
    }

    public TypeNode lookup(String id) throws MissingDeclarationException {
        //return null;
        throw new MissingDeclarationException("Missing declaration for ID: " + id + ".");
    }

    /**
     * @return the current active scope.
     */
//    private Map<String, STEntry> currentScope() {
//        return symbolTable.get(nestingLevel);
//    }


    /**
     * Extends the symbolTable with a new empty scope
     */
//    public List<Map<String,STEntry>> newScope() {
//        symbolTable.add(new HashMap<>());
//        nestingLevel += 1;
//    }


    /**
     * Extends the symbolTable with an initialized scope
     * @param  s  the scope to add
     */
//    public List<Map<String,STEntry>> newScope(Map<String,STEntry> s) {
//        symbolTable.add(s);
//        nestingLevel += 1;
//    }


    /*
     * if there is no clash of names, adds id ⟼ t to st
     * if it finds a collision needs to throw an exception
     */
    //public List<Map<String,STEntry>> addDecl(String id, Type t);


    /*
     looks for the type of id in st, if any
     */
    //Type lookup(String id);


    /**
     * Exits the current scope
     * TODO: la funzione del prof ritorna la sym table; ha senso cambiare il codice?
     */
//    public void exitScope() {
//        symbolTable.remove(nestingLevel);
//        nestingLevel--;
//    }

}
