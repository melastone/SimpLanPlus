package it.ghellimanca.ast.declaration;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;

/**
 * Node of the AST for a function declaration.
 */
public class DecFunNode implements Node {


    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
