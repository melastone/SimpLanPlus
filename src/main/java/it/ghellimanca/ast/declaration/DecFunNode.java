package it.ghellimanca.ast.declaration;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.MultipleDeclarationException;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.ArgNode;
import it.ghellimanca.ast.BlockNode;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Node of the AST for a function declaration.
 *
 * Has two attributes, which are mutually exclusive:
 * type is used for int/bool function
 * voidType is used in case the function is void.
 *
 * todo: controlla se il nodo viene creato correttamente in caso di funzioni void.
 */
public class DecFunNode extends DeclarationNode {

    final private TypeNode type;
    final private IdNode id;
    final private List<ArgNode> arguments;
    final private BlockNode body;


    public DecFunNode(TypeNode type, IdNode id, List<ArgNode> arguments, BlockNode body) {

        this.type = (TypeNode) type;    // Explicit cast cause type could also be VoidTypeNode, subclass of TypeNode
        this.id = id;
        this.arguments = arguments;
        this.body = body;
    }


    @Override
    public String toPrint(String indent) {

        String res = "\n" + indent + "DECFUN";

        if (type != null) {
                res += type.toPrint(indent + "\t") + id.toPrint(indent + "\t");
        }

        if (this.arguments != null) {
            for (ArgNode a : arguments) {
                res += a.toPrint(indent + "\t");
            }
        }
        res += body.toPrint(indent + "\t");
        return res;
    }

    @Override
    public String toString() { return toPrint("");}

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> err = new ArrayList<SemanticError>();

        try {
            env.addDeclaration(id.getIdentifier(), type);

            // Creates a new scope for params and function body
            env.newScope();

            for (ArgNode arg : arguments) {
                env.addDeclaration(arg.getId().getIdentifier(), arg.getType());
            }

            err.addAll(body.checkSemantics(env));
            env.exitScope();

        } catch (MultipleDeclarationException e) {
            err.add(new SemanticError(e.getMessage()));
        }

        return err;
    }

    @Override
    public TypeNode typeCheck() {
        return null;
    }
}
