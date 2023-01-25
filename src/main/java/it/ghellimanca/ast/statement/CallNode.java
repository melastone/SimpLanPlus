package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.ArgNode;
import it.ghellimanca.ast.type.ArrowTypeNode;
import it.ghellimanca.ast.type.VarTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Node of the AST for a call statement
 *
 * A call statement has the form:
 * ID '(' (exp(',' exp)*)? ')'
 *
 * todo: eliminare il controllo sulla dimensione di initPars
 *
 */
public class CallNode extends StatementNode {

    final private IdNode id;
    final private List<ExpNode> params;


    public CallNode(IdNode id, List<ExpNode> params) {
        this.id = id;
        this.params = params;
    }


    @Override
    public String toPrint(String indent) {
        String res = '\n' + indent + "CALL" + id.toPrint(indent + "\t");
        if (this.params != null) {
            for (ExpNode e : params){
                res += e.toPrint(indent + "\t");
            }
        }
        return res;
    }

    @Override
    public String toString() { return toPrint("");}

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) throws MissingInitializationException, ParametersCountException{
        ArrayList<SemanticError> err = new ArrayList<>();

        err.addAll(id.checkSemantics(env));

        // retrieve function STEntry from the Environment; we use safeLookup because checks have already been done by id.checkSemantics()
        STEntry funEntry = env.safeLookup(id.getIdentifier());

        // setting fun effect to used
        Environment updatedFunEnv = new Environment();
        updatedFunEnv.newScope();
        updatedFunEnv.safeAddDeclaration(id.getIdentifier(), funEntry.getType(), Effect.USED);

        env.seq(updatedFunEnv); // env ▷ [funId → USED]

        if (this.params != null) {

            // verify if variables that appear in parameters are declared
            List<IdNode> varsInParams = new ArrayList<>();
            for (ExpNode par : params) {
                varsInParams.addAll(par.variables());
            }
            for (IdNode var : varsInParams) {
                err.addAll(var.checkSemantics(env));
            }

            List<TypeNode> formalParamsTypes = ((ArrowTypeNode) funEntry.getType()).getArgs();
            int size = formalParamsTypes.size();
            if (params.size() != size) {
                throw new ParametersCountException("Function " + id.getIdentifier() + ": expecting " + size + " number of parameters but got " + params.size()  + " instead.");
            }

            List<Effect> codomainStatus = funEntry.getFunStatus().get(1);
            List<Boolean> initPars = funEntry.getInitPars();

            TypeNode funType = funEntry.getType();
            List<Integer> indexOfPassedByValue = IntStream
                    .range(0, params.size())
                    .filter(i -> !(((ArrowTypeNode) funType).getArgs().get(i) instanceof VarTypeNode))
                    .boxed()
                    .collect(Collectors.toList());
            List<Integer> indexOfPassedByReference = IntStream
                    .range(0, params.size())
                    .filter(i -> (((ArrowTypeNode) funType).getArgs().get(i) instanceof VarTypeNode))
                    .boxed()
                    .collect(Collectors.toList());

            // check that params passed by value have not an error status
            for (int i : indexOfPassedByValue) {
                if (codomainStatus.get(i).equals(new Effect(Effect.ERROR))) {
                    // can't use the following access to funNode, so we replace the message with a less accurate one
                    // throw new MissingInitializationException("Error in the effect analysis: function argument " + id.getStEntry().getFunNode().getArgument(i) + " was used before initialization inside function body.");
                    throw new MissingInitializationException("Error in the effect analysis: one argument of function " + id.getIdentifier() + " was used before initialization inside function body.");
                }
            }

            // set to init statuses of params initialized inside the function body
            for (int i : indexOfPassedByReference) {
                if (initPars.size() > 0) {
                    if (initPars.get(i)){
                        IdNode u_iId = params.get(i).variables().get(0);
                        STEntry u_iEntry = env.safeLookup(u_iId.getIdentifier());
                        u_iEntry.setVarStatus(Effect.seq(u_iEntry.getVarStatus(), new Effect(Effect.INIT)));
                    }
                }
            }

            // update statuses of params passed by value
            Environment Sigma1 = new Environment(env);

            List<IdNode> varsInExpressions = IntStream
                    .range(0, params.size())
                    .filter(i -> indexOfPassedByValue.contains(i))
                    .mapToObj(j -> params.get(j))
                    .flatMap(par -> par.variables().stream())
                    .collect(Collectors.toList());

            for (IdNode var : varsInExpressions) {
                //get access to Id entry in Sigma1 and set status with seq(Sigma1(var),used)
                STEntry varEntry = Sigma1.safeLookup(var.getIdentifier());
                varEntry.setVarStatus(Effect.seq(varEntry.getVarStatus(), new Effect(Effect.USED)));

                Sigma1.safeUpdateEntry(var.getIdentifier(), varEntry);
            }

            // update statuses of params passed by reference
            Environment Sigma2 = new Environment();
            List<Environment> res = new ArrayList<>();

            for (int i : indexOfPassedByReference) {
                Environment u_iEnv = new Environment();
                u_iEnv.newScope();

                IdNode u_iId = params.get(i).variables().get(0);
                STEntry u_iEntry = env.safeLookup(u_iId.getIdentifier());

                u_iEnv.safeAddDeclaration(u_iId.getIdentifier(), u_iEntry.getType());

                Effect u_iStatus = u_iEntry.getVarStatus();
                Effect x_iStatus = codomainStatus.get(i);

                u_iEntry.setVarStatus(Effect.seq(u_iStatus,x_iStatus));
                u_iEnv.safeUpdateEntry(u_iId.getIdentifier(), u_iEntry);

                res.add(u_iEnv);
            }

            if (res.size() > 0) {
                Sigma2 = res.get(0);
                for (int i = 1; i < res.size(); i++) {
                    Sigma2.par(res.get(i));
                }
            }

            // update environment to be returned
            Environment updatedEnv = Environment.update(Sigma1, Sigma2);
            System.out.println("Function call updated Environment to:\n" + updatedEnv);
            env.replace(updatedEnv);
        }

        return err;
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        TypeNode funType = id.typeCheck();

        if (!(funType instanceof ArrowTypeNode)) {
            throw new TypeCheckingException("Function " + id.getIdentifier() + " does not have a function type.");
        }

        List<TypeNode> formalParamsTypes = ((ArrowTypeNode) funType).getArgs();
        int size = formalParamsTypes.size();

        for (int i = 0; i < size; i++) {
            TypeNode expType = params.get(i).typeCheck();
            TypeNode parType = formalParamsTypes.get(i);

            if (parType instanceof VarTypeNode) {
                parType = ((VarTypeNode) parType).getType();
            }

            if (!expType.equals(parType)) {
                throw new TypeCheckingException("Function " + id.getIdentifier() + ": expecting argument of type " + parType + " but got " + expType + " instead.");
            }

        }

        return ((ArrowTypeNode) funType).getRet();
    }

//    @Override
//    public TypeNode typeCheck() throws TypeCheckingException {
//        TypeNode funType = id.typeCheck();
//
//        if (!(funType instanceof ArrowTypeNode)) {
//            throw new TypeCheckingException("Function " + id.getIdentifier() + " does not have a function type.");
//        }
//
//        List<TypeNode> formalParamsTypes = ((ArrowTypeNode) funType).getArgs();   //T1 x .. x Tn
//        List<TypeNode> actualParamsTypes = new ArrayList<>();
//
//        for (ExpNode par : params) {
//            TypeNode parType = par.typeCheck();
//
//            if (parType instanceof VarTypeNode) {
//                actualParamsTypes.add(((VarTypeNode) parType).getType());
//            }
//            else { actualParamsTypes.add(parType);}
//        }
//
//        int size = formalParamsTypes.size();
//        if (actualParamsTypes.size() != size) {
//            throw new TypeCheckingException("Function " + id.getIdentifier() + ": expecting " + size + " number of parameters but got " + actualParamsTypes.size()  + " instead.");
//        }
//
//        for (int i = 0; i < size; i++) {
//            if (!actualParamsTypes.get(i).equals(formalParamsTypes.get(i))) {
//                throw new TypeCheckingException("Function " + id.getIdentifier() + ": expecting argument of type " + formalParamsTypes.get(i) + " but got " + actualParamsTypes.get(i) + " instead.");
//            }
//        }
//
//        return ((ArrowTypeNode) funType).getRet();
//    }

    /**
     * Gets all the variables used as parameters or inside them.
     *
     * @return  list of IdNodes, each for one variable
     */
    public List<IdNode> variables() {
        return params.stream().flatMap(exp -> exp.variables().stream()).collect(Collectors.toList());
    }
}
