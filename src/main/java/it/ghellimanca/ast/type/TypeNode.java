package it.ghellimanca.ast.type;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;

import java.util.ArrayList;

/**
 * A generic type node of the AST.
 */

public abstract class TypeNode implements Node {

    //TODO da implementare checkSemantics di TypeNode
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    //TODO da implementare typeCheck di TypeNode
    @Override
    public Node typeCheck() {
        return null;
    }
}
