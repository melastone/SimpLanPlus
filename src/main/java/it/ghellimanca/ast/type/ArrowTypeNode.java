package it.ghellimanca.ast.type;

import it.ghellimanca.semanticanalysis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a function type in the form of
 * T1 x.. x Tn -> T
 */
public class ArrowTypeNode extends TypeNode{

    private List<TypeNode> args;
    private TypeNode ret;


    public ArrowTypeNode(List<TypeNode> args, TypeNode ret) {
        this.args = args;
        this.ret = ret;
    }

    public List<TypeNode> getArgs() {
        return this.args;
    }

    public TypeNode getRet() {
        return this.ret;
    }

    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "TYPE: " +
                args.stream().map(TypeNode::toString).reduce("",
                        (arg1, arg2) -> (arg1.isEmpty() ? "" : (arg1 + " X ")) + arg2)
                + " -> " + ret;
    }

    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        return new ArrayList<>();
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }
}
