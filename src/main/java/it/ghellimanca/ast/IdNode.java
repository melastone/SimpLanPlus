package it.ghellimanca.ast;


import it.ghellimanca.Environment;

import java.util.ArrayList;

/**
 * Represents an identifier in the AST.
 *
 * An identifier is a label made of character and/or digits,
 * which has to begin with a character.
 *
 * @todo: does it make sense to have the empty constructor?
 */
public class IdNode implements Node{

    final private String id;

    public IdNode(){
        this.id = null;
    }

    public IdNode(final String id) {
        this.id = id;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
