package it.ghellimanca.ast;

import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.ast.type.VarTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.semanticanalysis.errors.*;

import java.util.ArrayList;


/**
 * Node of the AST for an identifier.
 *
 */
public class IdNode implements Node {

    final private String id;
    private STEntry stEntry;

    private int currNestingLevel;


    public IdNode(String id) {
        this.id = id;
    }



    public String getIdentifier() {
        return id;
    }

    public int getCurrNestingLevel() {
        return currNestingLevel;
    }

    public void setCurrNestingLevel(int currNestingLevel) {
        this.currNestingLevel = currNestingLevel;
    }

    public STEntry getStEntry() {
        return stEntry;
    }

    public void setStEntry(STEntry stEntry) {
        this.stEntry = stEntry;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "ID: " + id;
    }


    @Override
    public String toString() {
        return toPrint("");
    }

    // will be called only when id is used not declared
    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        ArrayList<SemanticWarning> err = new ArrayList<>();

        stEntry = env.lookup(id); // lookup has to return the type of the value that the id identifies
        currNestingLevel = env.getNestingLevel();

        return err;
    }


    @Override
    public TypeNode typeCheck() {
        TypeNode type = stEntry.getType();

        if (type instanceof VarTypeNode) type = ((VarTypeNode) type).getType();

        return type;
    }

    @Override
    public String codeGeneration() {
        StringBuilder buffer = new StringBuilder();
        int idNestingLevel = stEntry.getNestingLevel();
        int idOffset = stEntry.getOffset();

        buffer.append("mv $al $fp").append("\n");

        for (int i = 0; i < currNestingLevel - idNestingLevel; i++) {
            buffer.append("lw $al 0($al)").append("\n");
        }
        buffer.append("lw $a0 ").append(idOffset + 2).append("($al)").append("\n");

        return buffer.toString();
    }
}
