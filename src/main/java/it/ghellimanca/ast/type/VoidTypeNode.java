package it.ghellimanca.ast.type;

import it.ghellimanca.ast.Node;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;

import java.util.ArrayList;

public class VoidTypeNode extends TypeNode{

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "TYPE: " + "void";
    }

    @Override
    public Node typeCheck() { return null; }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return new ArrayList<>();
    }
}
