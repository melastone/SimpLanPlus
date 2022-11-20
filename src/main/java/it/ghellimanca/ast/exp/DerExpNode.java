package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of dereferencing something as a node in the AST.
 */
public class DerExpNode extends ExpNode {

    final IdNode id;

    public DerExpNode(IdNode id) {
        this.id = id;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "DER_EXP" + id.toPrint(indent + "\t");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return id.checkSemantics(env);
    }

    @Override
    public TypeNode typeCheck() {
        return id.typeCheck();
    }

    @Override
    public List<IdNode> variables() {
        List<IdNode> variables = new ArrayList<>();

        variables.add(id);
        return variables;
    }
}
