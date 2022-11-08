package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.ArgNode;
import it.ghellimanca.ast.type.ArrowTypeNode;
import it.ghellimanca.ast.type.VarTypeNode;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

            if (!expType.equals(parType)) {
                throw new TypeCheckingException("Function " + id.getIdentifier() + ": expecting argument of type " + parType + " but got " + expType + " instead.");
            }

            // al momento il controllo sopra segnerà come diverse le coppie (int, &int), cosa che invece dovrebbe essere corretta.
            // per i parametri passati per riferimento infatti io ho come tipo del par FORMALE un VarTypeNode
            // ma come tipo del par ATTUALE il corrispondente TypeNode.
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
//        List<TypeNode> formalParamsTypes = ((ArrowTypeNode) funType).getArgs();
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

}
