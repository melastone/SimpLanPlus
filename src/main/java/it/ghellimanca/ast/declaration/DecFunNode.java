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
import java.util.stream.IntStream;

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


    public ArgNode getArgument(int index) {
        return arguments.get(index);
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
            id.setStEntry(env.addDeclaration(id.getIdentifier(), funType, Effect.INIT));
            id.getStEntry().setFunNode(this);   // adding reference to this node in order to access arguments during effects analysis

            STEntry funEntry = id.getStEntry();

            // build Sigma0 - domain of the function and saving it in the STEntry of the function
            List<Effect> Sigma0 = new ArrayList<>();
            Effect bottom = new Effect(Effect.DECLARED);
            for (int i = 0; i < arguments.size(); i++) {
                Sigma0.add(bottom);
            }
            funEntry.setFunStatus(0, Sigma0);

            // open a new scope where to evaluate function body and calculating Sigma1
            env.newScope(); // todo: verifica se aprire un nuovo environment (effect analysis) si puà sost con aprire un nuovo scope (scope checking)

            // adding arguments declarations into the new scope. set them to INIT in order to correctly calculate Sigma1
            for (ArgNode arg : arguments) {
                arg.getId().setStEntry(env.safeAddDeclaration(arg.getId().getIdentifier(), arg.getType(),Effect.INIT));
            }

            // adding function declaration into the new scope in order to enable recursive calls
            STEntry localFunEntry = env.safeAddDeclaration(id.getIdentifier(), type);
            localFunEntry.setFunNode(this);

            // adding funStatus to innerEntry
            localFunEntry.setFunStatus(0, Sigma0);
            localFunEntry.setFunStatus(1, Sigma0);

            // saving current Sigma1 in order to verify wheter if it changes or not
            List<Effect> oldSigma1 = localFunEntry.getFunStatus().get(1);

            // it will create a new scope with function body info only
            err.addAll(body.checkSemantics(env));

            // update Sigma1 in innerFunEntry with changes made by body evaluation
            List<Effect> updatedStatuses = arguments.stream().map(argNode -> env.safeLookup(argNode.getId().getIdentifier()).getVarStatus())
                    .collect(Collectors.toList());
            localFunEntry.setFunStatus(1, updatedStatuses);

            boolean hasCodomainChanged = !localFunEntry.getFunStatus().get(1).equals(oldSigma1);

            while (hasCodomainChanged) {

                oldSigma1 = localFunEntry.getFunStatus().get(1);

                err.addAll(body.checkSemantics(env));

                localFunEntry.setFunStatus(1, arguments.stream().map(argument -> env.safeLookup(argument.getId().getIdentifier()).getVarStatus())
                    .collect(Collectors.toList()));

                hasCodomainChanged = !localFunEntry.getFunStatus().get(1).equals(oldSigma1);
            }

            // build initPars
            List<Boolean> initPars = new ArrayList<>();
            for (int argIndex = 0; argIndex < arguments.size(); argIndex++) {
//                STEntry argEntry = arguments.get(argIndex).getId().getStEntry();
                STEntry argEntry = env.safeLookup(arguments.get(argIndex).getId().getIdentifier());
                initPars.add(argIndex, argEntry.isInitAfterDec());
            }

            // saving both Sigma1 and initPars in the STEntry of the function
            funEntry.setFunStatus(1,localFunEntry.getFunStatus().get(1));
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