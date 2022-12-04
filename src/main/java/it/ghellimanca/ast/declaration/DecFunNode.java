package it.ghellimanca.ast.declaration;

import it.ghellimanca.ast.type.ArrowTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.ArgNode;
import it.ghellimanca.ast.BlockNode;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;

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
 * todo: verifica che nella costruzione di Sigma0 i parametri abbiano effettivamente tutti status INIT
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
            // add function declaration in current scope
            List<TypeNode> argsType = arguments.stream().map(ArgNode::getType).collect(Collectors.toList());
            ArrowTypeNode funType = new ArrowTypeNode(argsType,type);
            id.setStEntry(env.addDeclaration(id.getIdentifier(), funType, Effect.DECLARED));
            STEntry funEntry = id.getStEntry();

            // build Sigma0 - domain of the function and saving it in the STEntry of the function
            List<Effect> Sigma0 = new ArrayList<>();
            Effect bottom = new Effect(Effect.DECLARED);
            for (int i = 0; i < arguments.size(); i++) {
                Sigma0.add(bottom);
            }
            funEntry.setFunStatus(0, Sigma0);

            // open a new scope where to evaluate function body and calculating Sigma1
            env.newScope();

            // adding arguments declarations into the new scope. set them to INIT in order to correctly calculate Sigma1
            for (ArgNode arg : arguments) {
                arg.getId().setStEntry(env.addDeclaration(arg.getId().getIdentifier(), arg.getType(),Effect.INIT));
            }

            // it will create a new scope with function body info only
            err.addAll(body.checkSemantics(env));

            // build both Sigma1 and initPars
            List<Effect> Sigma1 = new ArrayList<>();
            List<Boolean> initPars = new ArrayList<>();
            for (int argIndex = 0; argIndex < arguments.size(); argIndex++) {
                STEntry argEntry = arguments.get(argIndex).getId().getStEntry();
                Sigma1.add(argIndex, argEntry.getVarStatus());
                initPars.add(argIndex, argEntry.isInitAfterDec());
            }

            // saving both Sigma1 and initPars in the STEntry of the function
            funEntry.setFunStatus(1,Sigma1);
            funEntry.setInitPars(initPars);

            env.popScope();

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

        return type;
    }
}