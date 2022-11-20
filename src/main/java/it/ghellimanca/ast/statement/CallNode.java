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
 * todo: aggiungere il controllo giusto sui tipi nel caso di VarTypeNode
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
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> err = new ArrayList<>();

        err.addAll(id.checkSemantics(env));

        if (this.params != null) {
            for (ExpNode par : params) {
                err.addAll(par.checkSemantics(env));
            }
        }

        List<Effect> domainStatus = id.getStEntry().getFunStatus().get(0);
        List<Effect> codomainStatus = id.getStEntry().getFunStatus().get(1);

        //todo: gestire il caso in cui params.size != funType.getArgs().size().. non abbiamo ancora fatto type check!!!
        TypeNode funType = id.getStEntry().getType();
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

        //Check that params passed by value have not an error status
        for (int i : indexOfPassedByValue) {
            if (codomainStatus.get(i).equals(Effect.ERROR)) {
                //Print Error here
            }
        }

        //Update status of params passed by value
        Environment Sigma1 = new Environment(env);

        List<IdNode> varsInExpressions = IntStream
                .range(0,params.size())
                .filter(i -> indexOfPassedByValue.contains(i))
                .mapToObj(j -> params.get(j))
                .flatMap(par -> par.variables().stream())
                .collect(Collectors.toList());

        System.out.println("Variables in parameters expression are");
        for (IdNode var : varsInExpressions) {
            System.out.println(var.getIdentifier());
            //get access to Id entry in Sigma1 and set status with seq(Sigma1(var),used)
        }

        //Update status of params passed by reference
        Environment Sigma2 = new Environment();
        List<Environment> res = new ArrayList<>();

        for (int i : indexOfPassedByReference) {
            Environment u_iEnv = new Environment();
            u_iEnv.newScope();

            IdNode u_iId = params.get(i).variables().get(0);
            u_iEnv.addDeclarationSafe(u_iId.getIdentifier(), u_iId.getStEntry().getType());

            Effect u_iStatus = u_iId.getStEntry().getVarStatus();
            Effect x_iStatus = codomainStatus.get(i);
            u_iId.getStEntry().setVarStatus(Effect.seq(u_iStatus,x_iStatus));

            res.add(u_iEnv);
        }

        if (res.size() > 0) {
            Sigma2 = res.get(0);
            for (int i = 0; i < res.size(); i++) {
                // fare la max a due a due..
                //sigma2 = Environment::operationsOnEnvironments(sigma2, res.get(i), Effect::max);
            }
        }

        //Update environment to be returned
        Environment updatedEnv = Environment.update(Sigma1,Sigma2);
        env.replace(updatedEnv);

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
        if (params.size() != size) {
            throw new TypeCheckingException("Function " + id.getIdentifier() + ": expecting " + size + " number of parameters but got " + params.size()  + " instead.");
        }

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

    public List<IdNode> variables() {
        return params.stream().flatMap(exp -> exp.variables().stream()).collect(Collectors.toList());
    }
}
