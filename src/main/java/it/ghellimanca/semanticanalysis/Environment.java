package it.ghellimanca.semanticanalysis;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.*;

/**
 * Represents the Symbol Table.
 * It is implemented as a list of hashtables.
 */

public class Environment {

    /*
     * Modifiers:
     * final cause it's not supposed to be overridden
     * private because outside of this class the code interacts
     * with the table via Environment class methods.
     *
     */
    final private List<Map<String,STEntry>> symbolTable;

    private int nestingLevel;

    private int offset;


    /**
     * Constructor for {@code Environment}
     *
     * @param symTable     a list of hashmaps (that can be empty)
     * @param nestingLevel a positive integer
     * @param offset       a positive integer
     */
    public Environment(List<Map<String, STEntry>> symTable, int nestingLevel, int offset) {
        this.symbolTable = symTable;
        this.nestingLevel = nestingLevel;
        this.offset = offset;
    }

    public Environment() {
        this.symbolTable = new ArrayList<>();
        this.nestingLevel = -1;
        this.offset = 0;
    }

    /**
     * Creates a copy of Environment env
     *
     * @param env   environment to be copied
     */
    public Environment(Environment env) {
        this(new ArrayList<>(), env.nestingLevel, env.offset);     // copying env global variables (n, o); init Symbol Table

        // copying Symbol Table
        for (var scope : env.symbolTable) {
            Map<String,STEntry> copyScope = new HashMap();

            for (var id : scope.keySet()) {
                copyScope.put(id, new STEntry(scope.get(id)));
            }
            this.symbolTable.add(copyScope);
        }
    }


    /**
     * @return the current active scope.
     */
    private Map<String, STEntry> currentScope() {
        return symbolTable.get(nestingLevel);
    }


    /**
     * Extends the symbolTable with a new empty scope
     *
     * @return the updated Symbol Table

     */
    public List<Map<String,STEntry>> newScope() {

        symbolTable.add(new HashMap<>());
        nestingLevel += 1;
        offset = 0;

        return this.symbolTable;
    }


    /**
     * Extends the symbolTable with an initialized scope
     *
     * @param  s  the scope to add
     * @return the updated Symbol Table
     */
    public List<Map<String,STEntry>> newScope(Map<String,STEntry> s) {

        symbolTable.add(s);
        nestingLevel += 1;
        offset = 0;

        return this.symbolTable;
    }

    public List<Map<String, STEntry>> getSymbolTable() {
        return symbolTable;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


    /**
     * Process a new declaration by adding its entry in the Symbol Table.
     * Adds id ⟼ t into the table, where id is the label of the variable (or function) and t is its type
     * If it finds a collision throws a MultipleDeclarationException
     *
     * @param id   the identifier of the variable or function.
     * @param type the type of the variable or function.
     * @throws MultipleDeclarationException when [id] is already present in the head
     *                                      of the Symbol Table.
     * @return the updated Symbol Table
     */
    public List<Map<String, STEntry>> addDeclaration(String id, TypeNode type) throws MultipleDeclarationException{
        STEntry stentry = new STEntry(type, nestingLevel, offset, new Effect());

        STEntry declaration = currentScope().put(id, stentry);
        if (declaration != null) {
            throw new MultipleDeclarationException("Multiple declaration for ID: " + id
                    + ". It was previously defined of type: " + declaration.getType() + ".");
        }

        offset += 1;    // 1 = 4 Byte, for integers and boolean (1/0)

        return this.symbolTable;
    }


    /**
     * Looks for the type of id in ST, if any
     *
     */
     public STEntry lookup(String id) throws MissingDeclarationException {
         for (int i = nestingLevel; i >= 0; i--) {
            Map<String, STEntry> scope = symbolTable.get(i);
            STEntry stEntry = scope.get(id);
            if (stEntry != null)
                return stEntry;
        }
        throw new MissingDeclarationException("Missing declaration for ID: " + id + ".");
     }


    /**
     * Exits the current scope
     */
    public void exitScope() {
        symbolTable.remove(nestingLevel);
        nestingLevel--;
        if (nestingLevel >= 0) {    // restore offset as it was in the previous scope
            var stEntry = symbolTable.get(nestingLevel).values().stream().max(Comparator.comparing(STEntry::getOffset));
            offset = stEntry.map(entry -> entry.getOffset() + 1).orElse(0);
        }
    }

    //MAX
    public Environment max(Environment env1, Environment env2) {
        return operationsOnEnvironments(env1, env2, Effect::max);
    }

    //SEQ
    public Environment seq(Environment env1, Environment env2) {
        return operationsOnEnvironments(env1, env2, Effect::seq);
    }

    //PAR
    public Environment par(Environment env1, Environment env2) {
        return operationsOnEnvironments(env1, env2, Effect::par);
    }

    //BIN
    public Environment bin(Environment env1, Environment env2) {
        return operationsOnEnvironments(env1, env2, Effect::bin);
    }

    Environment operationsOnEnvironments(Environment env1, Environment env2, BiFunction<Effect, Effect, Effect> operator) {
        Environment resEnv = new Environment(new ArrayList<>(), env1.getNestingLevel(), env1.getOffset());

        for (int i = (env1.getSymbolTable().size() - 1); i >= 0; i--) {
            var scope1 = env1.symbolTable.get(i);
            var scope2 = env2.symbolTable.get(i);

            HashMap<String, STEntry> resScope = new HashMap<>();

            for (var id : scope1.keySet()) {
                var entry1 = scope1.get(id);
                var entry2 = scope2.get(id);

                if (entry2 == null) {  // x non appartiene dom(sigma')
                    resScope.put(id, entry1);
                } else if (entry1 == null) { // x non appartiene dom(sigma)
                    resScope.put(id, entry2);
                }
                else {
                    var entryOp = new STEntry(entry1.getType(), entry1.getNestingLevel(), entry1.getOffset());
                    entryOp.setStatus(operator.apply(entry1.getStatus(), entry2.getStatus()));
                    resScope.put(id, entryOp);
                }
            }
            resEnv.symbolTable.add(resScope);
        }

        return resEnv;
    }

    //Update


}
