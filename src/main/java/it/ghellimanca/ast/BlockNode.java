package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.Node;

import java.util.ArrayList;

/**
 * Node of the AST for a block
 *
 * @todo: add list of decl and list of stats after defining them
 * @todo: implement the class
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