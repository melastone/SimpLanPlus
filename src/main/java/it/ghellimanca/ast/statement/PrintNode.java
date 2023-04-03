package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.type.VoidTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.errors.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Node of the AST for a print statement
 *
 * A print statement has the form:
 * 'print' exp
 *
 */
public class PrintNode extends StatementNode {

    final private ExpNode exp;


    public PrintNode(ExpNode exp) {
        this.exp = exp;
    }



    @Override
    public String toPrint(String indent) {
        return "\n" + indent + "PRINT" + exp.toPrint(indent + "\t");
    }


    @Override
    public String toString() { return toPrint("");}


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersException {
        return exp.checkSemantics(env);
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        exp.typeCheck();

        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration() {
        return exp.codeGeneration() + "print $a0\n";
    }

    @Override
    public List<IdNode> variables() {
        return exp.variables();
    }
}
