package it.ghellimanca.ast.declaration;

import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.errors.*;

import java.util.ArrayList;


/**
 * Node of the AST for a variable declaration
 *
 * A variable declaration has the form:
 * type ID ('=' exp)? ';' ;
 *
 */

public class DecVarNode extends DeclarationNode {

    final private TypeNode type;
    final private IdNode id;
    final private ExpNode exp;



    public DecVarNode(TypeNode type, IdNode id, ExpNode exp) {
        this.type = type;
        this.id = id;
        this.exp = exp;
    }


    public IdNode getId() {
        return id;
    }

    public TypeNode getType() {
        return type;
    }


    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "DECVAR" + type.toPrint(indent + "\t") + id.toPrint(indent + "\t");
        if (this.exp != null) {
            res += exp.toPrint(indent + "\t");
        }
        return res;
    }


    @Override
    public String toString() { return toPrint("");}


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {

        ArrayList<SemanticWarning> err = new ArrayList<>();

        if (exp != null) {  // if the variable is also initialized
                err.addAll(exp.checkSemantics(env));
                id.setStEntry(env.addDeclaration(id.getIdentifier(), type, Effect.INIT));

            } else {
                id.setStEntry(env.addDeclaration(id.getIdentifier(), type, Effect.DECLARED));
            }

        return err;
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        if (exp != null) {
            TypeNode expNodeType = exp.typeCheck();

            if (!type.equals(expNodeType)){
                throw new TypeCheckingException("Type mismatch: expression " + exp + " type does not match variable " + id.getIdentifier() + " type.");
            }
        }

        return type;
    }


    @Override
    public String codeGeneration() {
        StringBuilder buff = new StringBuilder();

        // check if it's init, then set value
        if (exp != null) {
            buff.append(exp.codeGeneration());
            buff.append("sw $a0 ").append(id.getStEntry().getOffset() + 2).append("($fp)\n");
        }

        return buff.toString();
    }

}
