package it.ghellimanca.ast.declaration;

import it.ghellimanca.ast.statement.StatementNode;
import it.ghellimanca.ast.type.ArrowTypeNode;
import it.ghellimanca.ast.type.VarTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.ArgNode;
import it.ghellimanca.ast.BlockNode;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.errors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


/**
 * Node of the AST for a function declaration.
 *
 * Has two attributes, which are mutually exclusive:
 * type is used for int/bool function
 * voidType is used in case the function is void.
 *
 * todo: aggiungi i controlli di subtyping per le regole di tipaggio (non sarebbero necessarie x questa grammatica, ma il prof le apprezza)
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
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        ArrayList<SemanticWarning> err = new ArrayList<>();

        List<TypeNode> argsType = arguments.stream().map(ArgNode::getType).collect(Collectors.toList());

        ArrowTypeNode funType = new ArrowTypeNode(argsType,type);
        id.setStEntry(env.addDeclaration(id.getIdentifier(), funType, Effect.INIT));

        body.setFunctionBody(true);
        body.setFunId(id.getIdentifier());

        STEntry funEntryCopy = id.getStEntry();

        // build Sigma0 - domain of the function - and initPars
        List<Effect> Sigma0 = new ArrayList<>();
        List<Boolean> initPars = new ArrayList<>();

        Effect bottom = new Effect(Effect.DECLARED);
        for (int i = 0; i < arguments.size(); i++) {
            Sigma0.add(bottom);
            initPars.add(i, false);
        }

        // saving Sigma0 and initPars in the STEntry of the function
        funEntryCopy.setFunStatus(0, Sigma0);
        funEntryCopy.setInitPars(initPars);


        env.newScope();

        // adding fun body variable declarations into the new scope
        // doing this now cause we want offsets to be calculate in reverse order
        var varDecsInsideFunBody = body.getVariableDeclarations();
        for (DecVarNode dec : varDecsInsideFunBody) {
            err.addAll(dec.checkSemantics(env));
        }

        // adding arguments declarations into the new scope. set them to INIT in order to correctly calculate Sigma1
        List<IdNode> argIds = new ArrayList<>();
        for (int argId = 0; argId < arguments.size(); argId++) {
            var arg = arguments.get(argId);
            argIds.add(arg.getId());
            var isVar = argsType.get(argId) instanceof VarTypeNode;

            arg.getId().setStEntry(env.safeAddDeclaration(arg.getId().getIdentifier(), arg.getType(), Effect.INIT));

            if (isVar) {
                env.setOffset(env.getOffset() + 1);
            }

        }

        // adding function declaration into the new scope in order to enable recursive calls
        STEntry localFunEntryCopy = env.safeAddDeclaration(id.getIdentifier(), funType, Effect.INIT);

        // adding funStatuses and initialized initPars to innerEntry
        localFunEntryCopy.setFunStatus(0, Sigma0);
        localFunEntryCopy.setFunStatus(1, Sigma0);
        localFunEntryCopy.setInitPars(initPars);

        // saving current Environment in order to use it for eventual iterations of Fixed Point Algorithm
        Environment oldEnv = new Environment(env);

        // saving current Sigma1 in order to verify whether if it changes or not
        List<Effect> oldSigma1 = localFunEntryCopy.getFunStatus().get(1);


        var statementsInsideFunBody = body.getStatements();
        for (StatementNode stm : statementsInsideFunBody) {
            err.addAll(stm.checkSemantics(env));
        }

        // check that variables outside fun scope are not used
        var varIdInsideFunBody = body.getVarDeclarations();
        var vars = body.variables();
        for (int i=0; i < vars.size(); i++){
            if (!found(vars.get(i), varIdInsideFunBody, argIds)) {
                throw new MissingDeclarationException("Function " + id.getIdentifier() + " cannot access variable " + vars.get(i).getIdentifier() + " because it was declared outside function scope.\n");
            }
        }

        // update Sigma1 with changes made by body evaluation
        List<Effect> updatedStatuses = arguments.stream().map(argNode -> env.safeLookup(argNode.getId().getIdentifier()).getVarStatus())
                .collect(Collectors.toList());
        localFunEntryCopy.setFunStatus(1, updatedStatuses);

        boolean hasCodomainChanged = !localFunEntryCopy.getFunStatus().get(1).equals(oldSigma1);

        while (hasCodomainChanged) {

            // we do not want to keep statuses as they were update by previous iteration of the algorithm,
            // so we restore the environment as it was before
            env.replace(oldEnv);

            oldSigma1 = localFunEntryCopy.getFunStatus().get(1);

            // repeating the check of the body
            for (StatementNode stm : statementsInsideFunBody) {
                err.addAll(stm.checkSemantics(env));
            }

            localFunEntryCopy.setFunStatus(1, arguments.stream().map(argument -> env.safeLookup(argument.getId().getIdentifier()).getVarStatus())
                    .collect(Collectors.toList()));

            var newSigma1 = localFunEntryCopy.getFunStatus().get(1);

            hasCodomainChanged = !newSigma1.equals(oldSigma1);
        }

        // update initPars
        for (int argIndex = 0; argIndex < arguments.size(); argIndex++) {
            STEntry argEntry = env.safeLookup(arguments.get(argIndex).getId().getIdentifier());
            initPars.set(argIndex, argEntry.isInitAfterDec());
        }

        // saving both Sigma1 and initPars in the copied STEntry
        funEntryCopy.setFunStatus(1, localFunEntryCopy.getFunStatus().get(1));
        funEntryCopy.setInitPars(initPars);
        id.setStEntry(funEntryCopy);

        // before popping current scope, check that there are no variables whose status are either error or not used
        var currentScope = env.currentScope();  // getting sigma_1'' from sigma'' = sigma_0'' ⋅ sigma_1''

        for (var varId : currentScope.keySet()) {           // for every x_i in dom(sigma_1'')

            var idEntry = currentScope.get(varId);
            var idStatus = idEntry.getVarStatus();

            if (idStatus.equals(new Effect(Effect.ERROR))) {    // sigma_1''(x) has to be <= USED otherwise there was an error
                throw new MissingInitializationException(varId + " was used before initialization.");
            } else if (!varId.equals(id.getIdentifier()) && idStatus.equals(new Effect(Effect.INIT)) || idStatus.equals(new Effect(Effect.DECLARED))) {
                    err.add(new SemanticWarning("Variable " + varId + " was declared but never used."));
            }
        }

        env.popScope();

        // update function STEntry
        env.safeUpdateEntry(id.getIdentifier(), id.getStEntry());

        return err;
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        if (!type.equals(body.typeCheck())) {
            throw new TypeCheckingException("Type mismatch: function " + id.getIdentifier() + " does not return " + type + " type.");
        }

        return type;
    }

    @Override
    public String codeGeneration() {
        StringBuilder buff = new StringBuilder();

        // creating jump label in order to ignore the following code
        // it will be only used by the caller
        String jumpLabel = id.getIdentifier().toUpperCase() + "_JUMP";
        buff.append("b ").append(jumpLabel).append('\n');

        buff.append(id.getIdentifier().toUpperCase()).append("_ENTRY:\n");

        // allocate variable declarations
        var decs = new ArrayList<>(body.getVariableDeclarations());
        int nDecs = 0;
        if (decs.size() > 0) {
            nDecs = decs.size();
            buff.append("subi $sp $sp ").append(decs.size()).append('\n');
        }

        buff.append("push $ra\n");
        buff.append("push $fp\n");
        buff.append("mv $fp $sp\n");


        // generate code for body's declarations (only vars)
        decs.forEach(dec -> buff.append(dec.codeGeneration()));

        // generate code for body's statements
        var funStatements = new ArrayList<>(body.getStatements());
        funStatements.forEach(stm -> buff.append(stm.codeGeneration()));

        // setting label for after-return code
        buff.append(id.getIdentifier().toUpperCase(Locale.ROOT)).append("_END:\n");

        // update passed-by-reference variables values
        int nVar = 0, nArgs = 0;
        if (arguments != null && arguments.size() > 0) {
            nArgs = arguments.size();
            for (ArgNode arg: arguments) {
                boolean isVar = (arg.getType() instanceof VarTypeNode);
                if (isVar) {
                    nVar++;
                    int argOffset = arg.getId().getStEntry().getOffset();
                    buff.append("lw $t0 ").append(argOffset + 2).append("($fp)\n");     // get its value
                    buff.append("lw $t1 ").append(argOffset + 3).append("($fp)\n");     // get its address
                    buff.append("sw $t0 0($t1)\n");
                }
            }
        }

        // restore registers and stack
        buff.append("lw $fp 0($sp)\n");
        buff.append("pop\n");   // pop old $fp
        buff.append("lw $ra 0($sp)\n");
        buff.append("pop\n");   // pop RA
        buff.append("addi $sp $sp ").append(nArgs + nVar + nDecs).append('\n');

        buff.append("jr $ra\n");

        buff.append(jumpLabel).append(":\n");

        return buff.toString();
    }

    public boolean found(IdNode id, List<IdNode> l1, List<IdNode> l2) {
        var tmp = false;

        for (IdNode el1 : l1) {
            if (id.getIdentifier().equals(el1.getIdentifier())) {
                tmp = true;
                break;
            }
        }

        for (IdNode el2 : l2) {
            if (id.getIdentifier().equals(el2.getIdentifier())) {
                tmp = true;
                break;
            }
        }

        return tmp;
    }
}