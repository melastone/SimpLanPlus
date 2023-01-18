package it.ghellimanca.ast.declaration;

import it.ghellimanca.ast.type.ArrowTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.ArgNode;
import it.ghellimanca.ast.BlockNode;
import it.ghellimanca.ast.IdNode;
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
    public ArrayList<SemanticError> checkSemantics(Environment env) throws MissingInitializationException, ParametersCountException {
        ArrayList<SemanticError> err = new ArrayList<>();

        try {

            // add function declaration in current scope
            List<TypeNode> argsType = arguments.stream().map(ArgNode::getType).collect(Collectors.toList());
            ArrowTypeNode funType = new ArrowTypeNode(argsType, type);

            /*id.setStEntry(env.addDeclaration(id.getIdentifier(), funType, Effect.INIT));
            id.getStEntry().setFunNode(this);   // adding reference to this node in order to access arguments during effects analysis*/

            // AGGIUNTO
            id.setStEntry(env.addDeclaration(id.getIdentifier(), funType, Effect.INIT));
            STEntry funEntryCopy = id.getStEntry(); //this is a copy

            // build Sigma0 - domain of the function and saving it in the STEntry of the function
            List<Effect> Sigma0 = new ArrayList<>();
            Effect bottom = new Effect(Effect.DECLARED);
            for (int i = 0; i < arguments.size(); i++) {
                Sigma0.add(bottom);
            }

            /*funEntry.setFunStatus(0, Sigma0);*/

            // AGGIUNTO
            funEntryCopy.setFunStatus(0, Sigma0);
            id.setStEntry(funEntryCopy); // settato solo nella entry dell'idnode non nella tabella

            // open a new scope where to evaluate function body and calculating Sigma1
            env.newScope();

            // adding arguments declarations into the new scope. set them to INIT in order to correctly calculate Sigma1
            for (ArgNode arg : arguments) {
                arg.getId().setStEntry(env.safeAddDeclaration(arg.getId().getIdentifier(), arg.getType(), Effect.INIT));
            }

            // adding function declaration into the new scope in order to enable recursive calls
            STEntry localFunEntry = env.safeAddDeclaration(id.getIdentifier(), funType);
            // all after this will not be in the table info about funentry
            localFunEntry.setFunNode(this);
            // adding funStatus to innerEntry
            localFunEntry.setFunStatus(0, Sigma0);
            localFunEntry.setFunStatus(1, Sigma0);

            // saving current Environment in order to use it for eventual iterations of Fixed Point Algorithm
            Environment oldEnv = new Environment(env);

            // saving current Sigma1 in order to verify whether if it changes or not
            List<Effect> oldSigma1 = localFunEntry.getFunStatus().get(1);

            // it will create a new scope with function body info only
            err.addAll(body.checkSemantics(env));

            // update Sigma1 in innerFunEntry with changes made by body evaluation
            List<Effect> updatedStatuses = arguments.stream().map(argNode -> env.safeLookup(argNode.getId().getIdentifier()).getVarStatus())
                    .collect(Collectors.toList());
            localFunEntry.setFunStatus(1, updatedStatuses);

            boolean hasCodomainChanged = !localFunEntry.getFunStatus().get(1).equals(oldSigma1);

            while (hasCodomainChanged) {

                // we do not want to keep statuses as they were update by previous iteration of the algorithm,
                // so we restore the environment as it was before
                env.replace(oldEnv);

                // after replacement, funEntry has Sigma1 = Sigma0, so we restore it to last iteration Sigma1

                // TODO: this will not change the table and non dovrebbe essere necessario perchè neanche prima funstatus veniva settato nella tabell, valutare se cancellare
                env.safeLookup(id.getIdentifier()).setFunStatus(1, localFunEntry.getFunStatus().get(1));

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

            /* saving both Sigma1 and initPars in the STEntry of the function
            funEntry.setFunStatus(1,localFunEntry.getFunStatus().get(1));
            funEntry.setInitPars(initPars);*/

            //TODO: aggiornare nel modo giusto entry della funzione! valutare se fare primo o dopo popscope
            funEntryCopy.setFunStatus(1, localFunEntry.getFunStatus().get(1));
            funEntryCopy.setInitPars(initPars);
            id.setStEntry(funEntryCopy);
            // QUI:     update id nella tabella dell'env

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