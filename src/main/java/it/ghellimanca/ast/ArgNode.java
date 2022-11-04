package it.ghellimanca.ast;

import it.ghellimanca.ast.type.VoidTypeNode;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;

/**
 * Represents an argument of a function node in the AST.
 *
 * type ID | var type ID
 *
 * todo: inserire il riferimento esplicito nel caso di arg passati per riferimento
 */
public class ArgNode implements Node {

    final TypeNode type;
    final IdNode id;

    public ArgNode(TypeNode type, IdNode id) {
        this.type = type;
        this.id = id;
    }

    public IdNode getId() {
        return id;
    }

    public TypeNode getType() {
        return type;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {

        return new ArrayList<>();
    }

    @Override
    public TypeNode typeCheck() {
        return new VoidTypeNode();    // has nothing to return
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "ARGUMENT" + type.toPrint(indent + "\t") + id.toPrint(indent + "\t");
    }

    @Override
    public String toString() {
        return toPrint("");
    }

}
