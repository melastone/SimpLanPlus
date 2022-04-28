package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.ast.Node;

import java.util.ArrayList;

/**
 * Represents a block in the Abstract Syntax Tree (AST)
 */
public class BlockNode implements Node{

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
