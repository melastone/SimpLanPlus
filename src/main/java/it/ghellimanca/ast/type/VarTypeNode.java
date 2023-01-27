package it.ghellimanca.ast.type;

import it.ghellimanca.semanticanalysis.*;

import java.util.ArrayList;

public class VarTypeNode extends TypeNode{

    final TypeNode type;

    public VarTypeNode(TypeNode type) {
        this.type = type;
    }

    public TypeNode getType() {
        return type;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "TYPE: " + "&" + type;
    }

    @Override
    public TypeNode typeCheck() { return null; }

    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersCountException {
        return new ArrayList<>();
    }
}
