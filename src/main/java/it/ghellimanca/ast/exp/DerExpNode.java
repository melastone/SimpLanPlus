package it.ghellimanca.ast.exp;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of dereferencing something as a node in the AST.
 *
 * todo: capisci se la codeGen deve fare altro
 */
public class DerExpNode extends ExpNode {



    final IdNode id;

    public DerExpNode(IdNode id) {
        this.id = id;
    }

    public IdNode getId() {
        return id;
    }


    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "DER_EXP" + id.toPrint(indent + "\t");
    }


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersCountException {

        // first checking the semantic errors
        ArrayList<SemanticWarning> err = id.checkSemantics(env);

        // dummy environment for status change of the id
        Environment newEnv = new Environment();
        newEnv.newScope();
        newEnv.safeAddDeclaration(id.getIdentifier(), id.getStEntry().getType(), Effect.USED);

        // replace old env with seq of old env and dummy env
        env.seq(newEnv);

        // set new status in the entry inside idnode
        id.setStEntry(env.safeLookup(id.getIdentifier()));

        return err;
    }


    @Override
    public TypeNode typeCheck() {
        return id.typeCheck();
    }

    @Override
    public String codeGeneration() {
        // non ho codice qui perché l'operazione di cercare il valore di x e metterlo in $0 già la fa idNode

        return id.codeGeneration();
    }


    @Override
    public List<IdNode> variables() {
        List<IdNode> variables = new ArrayList<>();

        variables.add(id);
        return variables;
    }
}
