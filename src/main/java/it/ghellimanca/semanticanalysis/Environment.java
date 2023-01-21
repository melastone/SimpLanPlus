package it.ghellimanca.semanticanalysis;

import java.util.*;
import java.util.function.BiFunction;

import it.ghellimanca.ast.type.ArrowTypeNode;
import it.ghellimanca.ast.type.TypeNode;


/**
 * Represents the Symbol Table.
 * It is implemented as a list of hashtable.
 *
 */

public class Environment {

    private int nestingLevel;

    private int offset;

    final private List<Map<String,STEntry>> symbolTable;



    /**
     * Constructor for {@code Environment}.
     * Creates a new empty Environment.
     *
     */
    public Environment() {
        this.symbolTable = new ArrayList<>();
        this.nestingLevel = -1;
        this.offset = 0;
    }


    /**
     * Constructor for {@code Environment}.
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


    /**
     * Constructor for {@code Environment}.
     * Creates a copy of Environment env.
     *
     * @param env   environment to be copied
     */
    public Environment(Environment env) {
        this(new ArrayList<>(), env.nestingLevel, env.offset);     // copying env global variables (n, o); init Symbol Table

        // copying Symbol Table
        for (var scope : env.symbolTable) {
            Map<String,STEntry> copyScope = new HashMap<>();

            for (var id : scope.keySet()) {
                copyScope.put(id, new STEntry(scope.get(id)));
            }
            this.symbolTable.add(copyScope);
        }
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


    /**
     * @return the current active scope.
     */
    public Map<String, STEntry> currentScope() {
        return symbolTable.get(nestingLevel);
    }


    /**
     * Extends the symbolTable with a new empty scope
     *
     */
    public void newScope() {
        symbolTable.add(new HashMap<>());
        nestingLevel += 1;
        offset = 0;
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


    /**
     * Adds a new scope to the environment.
     *
     * @param scope the scope to add to the Symbol Table stack
     */
    private void pushNewScope(Map<String, STEntry> scope) {
        symbolTable.add(scope);
        nestingLevel += 1;
        offset = 0;
    }


    /**
     * Pops the current active scope from the Symbol Table stack. Decrements the
     * nesting level. Sets the offset to the maximum offset in the previous scope +
     * 1.
     */
    public void popScope() {
        symbolTable.remove(nestingLevel);
        nestingLevel--;

        if (nestingLevel >= 0) {
            var stEntry = symbolTable.get(nestingLevel).values().stream().max(Comparator.comparing(STEntry::getOffset));
            offset = stEntry.map(entry -> entry.getOffset() + 1).orElse(0);
        }
    }


    /**
     * Process a new declaration by adding its entry in the Symbol Table.
     * Adds id ⟼ t into the table, where id is the label of the variable (or function) and t is its type
     * If it finds a collision throws a MultipleDeclarationException
     *
     * @param id     the identifier of the variable or function.
     * @param type   the type of the variable or function.
     * @param status a positive integer which will become the Effect status
     * @throws MultipleDeclarationException when [id] is already present in the head
     *                                      of the Symbol Table.
     * @return the updated Symbol Table
     */
    public STEntry addDeclaration(String id, TypeNode type, int status) throws MultipleDeclarationException{
        Effect effect = new Effect(status);
        STEntry stentry = new STEntry(type, nestingLevel, offset, effect);

        STEntry declaration = currentScope().put(id, stentry);
        if (declaration != null) {
            throw new MultipleDeclarationException("Multiple declaration for ID: " + id
                    + ". It was previously defined of type: " + declaration.getType() + ".");
        }

        offset += 1;    // 1 = 4 Byte, for integers and boolean (1/0)

        return stentry;
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
    public STEntry addDeclaration(String id, TypeNode type) throws MultipleDeclarationException{
        STEntry stentry = new STEntry(type, nestingLevel, offset);

        STEntry declaration = currentScope().put(id, stentry);
        if (declaration != null) {
            throw new MultipleDeclarationException("Multiple declaration for ID: " + id
                    + ". It was previously defined of type: " + declaration.getType() + ".");
        }

        offset += 1;

        return stentry;
    }


    /**
     * Process a new declaration by adding its entry in the Symbol Table.
     * Used when it is certain that [id] has not been declared before.
     * If it does unexpected behaviour could occur.
     *
     * @param id     the identifier of the variable or function.
     * @param type   the type of the variable or function.
     * @param status a positive integer which will become the Effect status
     * @return the updated Symbol Table
     */
    public STEntry safeAddDeclaration(String id, TypeNode type, int status) {
        Effect effect = new Effect(status);
        STEntry stentry = new STEntry(type, nestingLevel, offset, effect);

        currentScope().put(id, stentry);
        offset += 1;

        return stentry;
    }


    /**
     * Process a new declaration by adding its entry in the Symbol Table.
     * Used when it is certain that [id] has not been declared before.
     * If it does unexpected behaviour could occur.
     *
     * @param id   the identifier of the variable or function.
     * @param type the type of the variable or function.
     * @return the updated Symbol Table
     */
    public STEntry safeAddDeclaration(String id, TypeNode type) {
        STEntry stentry = new STEntry(type, nestingLevel, offset);

        currentScope().put(id, stentry);
        offset += 1;

        return stentry;
    }


    /**
     * Adds an entry to the current scope of the Symbol Table.
     * Used if sure that [id] has not been declared before.
     *
     * @param id        identifier of variable or function
     * @param stEntry   Symbol Table Entry associated to [id]
     */
    public void addEntry(String id, STEntry stEntry) {
        if(!(stEntry.getType() instanceof ArrowTypeNode)) {
            offset += 1;
        }
        currentScope().put(id,stEntry);
    }


    /**
     * Looks for the type of id in ST, if any.
     *
     * @param id    identifier to look for
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
     * Looks for the type of id in ST, if any.
     * Used when it is certain that [id] exists inside the Environment.
     * If it does not unexpected behaviour could occur.
     *
     * @param id    identifier to look for
     */
     public STEntry safeLookup(String id) {
         for (int i = nestingLevel; i >= 0; i--) {
             Map<String, STEntry> scope = symbolTable.get(i);
             STEntry stEntry = scope.get(id);
             if (stEntry != null)
                 return stEntry;
         }
         System.err.println("Missing declaration for ID: " + id + ". This should not happen as this is a safe lookup.\n");

         return null;
    }


    /**
     * Updates STEntry associated to [id] with [newEntry].
     * Used when it is certain that [id] exists inside the Environment.
     * If it does not unexpected behaviour could occur.
     *
     * @param id        identifier associated to the STEntry to update
     * @param newEntry  new STEntry, will update the old one
     * @return          the old STEntry, now updated
     */
    public STEntry safeUpdateEntry(String id, STEntry newEntry) {
        for (int i = nestingLevel; i >= 0; i--) {
            Map<String, STEntry> scope = symbolTable.get(i);
            STEntry oldEntry = scope.put(id, newEntry);
            if (oldEntry != null)
                return oldEntry;
        }
        System.err.println("Missing declaration for ID: " + id + ". This should not happen as this is a safe update.\n");

        return null;
    }


    /**
     * Replaces the current environment with another one
     *
     * @param env new environment that will replace the current one
     */
    public void replace(Environment env) {
        // copying env global variables (n, o); clearing Symbol Table
        this.symbolTable.clear();
        this.nestingLevel = env.getNestingLevel();
        this.offset = env.getOffset();

        // copying Symbol Table
        for (var scope : env.symbolTable) {
            Map<String,STEntry> copyScope = new HashMap<>();

            for (var id : scope.keySet()) {
                copyScope.put(id, new STEntry(scope.get(id)));
            }
            this.symbolTable.add(copyScope);
        }
    }


    /**
     * Exits the current scope by popping the head of the stack and updating global variables.
     *
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
    public void max(final Environment env2) {
        this.replace(operationsOnEnvironments(this, env2, Effect::max));
    }

    //SEQ
    public void seq(final Environment env2) {
        this.replace(operationsOnEnvironments(this, env2, Effect::seq));
    }

    //PAR
    public void par(final Environment env2) {
        this.replace(operationsOnEnvironments(this, env2, Effect::par));
    }

    //BIN
    public void bin(final Environment env2) {
        this.replace(operationsOnEnvironments(this, env2, Effect::bin));
    }


    public static Environment operationsOnEnvironments(final Environment env1, final Environment env2, BiFunction<Effect, Effect, Effect> operator) {
        Environment resEnv = new Environment(new ArrayList<>(), env1.nestingLevel, env1.offset);
        Environment tmp2 = new Environment(env2);

        // assuming dom(env2) is in dom(env1)
        for (int i = 0; i < env1.symbolTable.size() ; i++) {
            var scope1 = env1.symbolTable.get(i);
//            var scope2 = tmp2.symbolTable.get(i);

            HashMap<String, STEntry> resScope = new HashMap<>();

            for (var id : scope1.keySet()) {
                var entry1 = scope1.get(id);

                // looking for id inside scope2
                int j = 0;
                var foundId = false;

                while (!foundId && j < env2.symbolTable.size()) {
                    var scope2 = tmp2.symbolTable.get(j);
                    var entry2 = scope2.get(id);

                    foundId = entry2 != null;

                    if (foundId) {
                        var entryOp = new STEntry(entry1);
                        entryOp.setVarStatus(operator.apply(entry1.getVarStatus(), entry2.getVarStatus()));
                        resScope.put(id, entryOp);
                        tmp2.removeFirstIdentifier(id);
                    }

                    j++;
                }

                if (!foundId) {
                    resScope.put(id, entry1);
                }

//                for (int j = 0; j < env2.symbolTable.size(); j++) {
//                    var scope2 = tmp2.symbolTable.get(j);
//                    var entry2 = scope2.get(id);
//
////                    if (entry2 == null) {  // x non appartiene dom(sigma')
////                        resScope.put(id, entry1);
//                    if (entry2 != null) {    // id è anche in sigma2
//                        var entryOp = new STEntry(entry1.getType(), entry1.getNestingLevel(), entry1.getOffset());
//                        entryOp.setVarStatus(operator.apply(entry1.getVarStatus(), entry2.getVarStatus()));
//                        resScope.put(id, entryOp);
//                        tmp2.removeFirstIdentifier(id);
//                    }
//                }
            }
            resEnv.symbolTable.add(resScope);

        }

        // checking if there are elements left in env2
        for (int j = 0; j < tmp2.symbolTable.size(); j++) {
            var scope = tmp2.symbolTable.get(j);

            if (scope.keySet().size() > 0) {
                for (var id : scope.keySet()) {
                    resEnv.symbolTable.get(j).put(id, scope.get(id));
                    tmp2.removeFirstIdentifier(id);
                }
            }
        }

        return resEnv;
    }


    /**
     * Updates [env1] with new entries contained in [env2], if any.
     * It is used by CallNode for applying updates.
     * todo: controlla se il passo base viene eseguito in caso di env2 non vuoto
     *
     * @param env1  environment to be updated
     * @param env2  environment with updates
     * @return      updated environment
     */
    public static Environment update(Environment env1, Environment env2) {

        Environment finalEnv;

        if (env2.symbolTable.size()==0) {
            return new Environment(env1);
        }

        Map<String, STEntry> headScope1 = env1.symbolTable.get(env1.symbolTable.size()-1);
        Map<String, STEntry> headScope2 = env2.symbolTable.get(env2.symbolTable.size()-1);

        if (headScope2.keySet().isEmpty()) {
            return new Environment(env1);
        }

        Map.Entry<String, STEntry> u = headScope2.entrySet().stream().findFirst().get();
        env2.removeFirstIdentifier(u.getKey());

        if(headScope1.containsKey(u.getKey())) {
            headScope1.put(u.getKey(),u.getValue());

            finalEnv = update(env1,env2);
        } else {
            Environment uEnv = new Environment();
            uEnv.newScope();

            uEnv.safeAddDeclaration(u.getKey(), u.getValue().getType(), u.getValue().getVarStatus().getStatus());

            env1.popScope();
            Environment intermUpdateEnv = update(env1, uEnv);
            intermUpdateEnv.pushNewScope(headScope1);

            finalEnv = update(intermUpdateEnv, env2);

        }

        return finalEnv;
    }


    /**
     * Auxiliar function for {@link #update(Environment, Environment) update} method.
     * Removes first occurrence of entry [id] -> [STEntry] from this Environment.
     *
     * @param id    identifier of the entry to be removed
     */

    private void removeFirstIdentifier(String id) {
        for (int i = symbolTable.size() - 1; i >= 0; i--) {
            if (symbolTable.get(i).containsKey(id)) {
                symbolTable.get(i).remove(id);
                return;
            }
        }
    }

    @Override
    public String toString() {

        return "Environment{" +
                "nestingLevel=" + nestingLevel +
                ",\n offset=" + offset +
                ",\n symbolTable=" + symbolTable +
                "}\n";
    }
}
