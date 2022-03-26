package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;

import java.util.ArrayList;

// generic type node of the ast
public class TypeNode implements Node {

    //TODO da implementare checkSemantics di TypeNode
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    //TODO da implementare typeCheck di TypeNode
    @Override
    public Node typeCheck() {
        return null;
    }
}
