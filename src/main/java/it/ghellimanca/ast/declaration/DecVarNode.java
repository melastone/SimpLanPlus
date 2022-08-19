package it.ghellimanca.ast.declaration;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;


/**
 * Node of the AST for a variable declaration
 *
 * A variable declaration has the form:
 * type ID ('=' exp)? ';' ;
 *
 */

public class DecVarNode extends DeclarationNode {

    final private TypeNode type;
    final private IdNode id;
    final private ExpNode exp;


    public DecVarNode(TypeNode type, IdNode id, ExpNode exp) {
        this.type = type;
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "DECVAR" + type.toPrint(indent + "\t") + id.toPrint(indent + "\t");
        if (this.exp != null) {
            res += exp.toPrint(indent + "\t");
        }
        return res;
    }

    @Override
    public String toString() { return toPrint("");}

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public TypeNode typeCheck() {
        return null;
    }
}
