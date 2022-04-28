package it.ghellimanca.ast.declaration;

import it.ghellimanca.Environment;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;


/**
 * Represents a variable declaration in the AST.
 *
 * A variable has to be declared as follows:
 * type ID; OR type ID = exp;
 *
 */

public class DecVarNode implements Node {

    final private TypeNode type;
    final private

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
