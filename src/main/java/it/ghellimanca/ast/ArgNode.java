package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;

/**
 * Represents an argument of a function node in the AST.
 */
public class ArgNode implements Node {

    final TypeNode type;
    final int idNodeid; //TODO: cambiare ID ArgNode quando abbiamo eventuale ID node

    public ArgNode(TypeNode type, int idNodeid) {
        this.type = type;
        this.idNodeid = idNodeid;
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
