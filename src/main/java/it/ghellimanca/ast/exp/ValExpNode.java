package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.IntTypeNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a integer expression node in the AST.
 */
public class ValExpNode extends ExpNode {

    final int number;


    public ValExpNode(int number) {
        this.number = number;
    }

    public String toPrint(String indent) {
        return "\n" + indent + "VAL_EXP: " + number;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public TypeNode typeCheck() {
        return new IntTypeNode();
    }

    @Override
    public List<IdNode> variables() {
        return new ArrayList<>();
    }
}
