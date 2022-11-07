package it.ghellimanca.ast.declaration;

import it.ghellimanca.ast.type.ArrowTypeNode;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.MultipleDeclarationException;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.ArgNode;
import it.ghellimanca.ast.BlockNode;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Node of the AST for a function declaration.
 *
 * Has two attributes, which are mutually exclusive:
 * type is used for int/bool function
 * voidType is used in case the function is void.
 *
 * todo: aggiungi i controlli di subtyping per le regole di tipaggio (non sarebbero necessarie x questa grammatica, ma il prof le apprezza)
 * todo: testa la definizione di fun senza args
 * todo: stabilisci come viene utilizzato l'offset del corpo della funzione
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
            List<TypeNode> argsType = arguments.stream().map(ArgNode::getType).collect(Collectors.toList());
            ArrowTypeNode funType = new ArrowTypeNode(argsType,type);
            env.addDeclaration(id.getIdentifier(), funType);

            // creating an array of DecVar in order to add it to the body
            // so that the variables declared as args are saved in the same scope as the function body
            ArrayList<DecVarNode> params = new ArrayList<>();
            for (ArgNode arg : arguments) {
                DecVarNode dec = new DecVarNode(arg.getType(), arg.getId(), null);
                params.add(dec);
            }
            body.addDeclarations(params);

            // it will create a new scope with both params and function body info
            err.addAll(body.checkSemantics(env));

        } catch (MultipleDeclarationException e) {
            err.add(new SemanticError(e.getMessage()));
        }

        return err;
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        if (!type.equals(body.typeCheck())) {
            throw new TypeCheckingException("Type mismatch: function " + id.getIdentifier() + " does not return " + type + " type.");
        }

        return null;
    }
}
