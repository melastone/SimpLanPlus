package it.ghellimanca.ast;

import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.declaration.DeclarationNode;
import it.ghellimanca.ast.statement.StatementNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * Node of the AST for a block
 *
 *
 */
public class BlockNode extends StatementNode {

    final private List<DeclarationNode> declarations;
    final private List<StatementNode> statements;

//
//    /*
//    Constructor for an empty block
//     */
//    public BlockNode() {
//        this.declarations = null;
//        this.statements = null;
//    }

    /*
    Constructor
     */
    public BlockNode(List<DeclarationNode> declarations, List<StatementNode> statements) {
        this.declarations = declarations;
        this.statements = statements;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "BLOCK";

        if (this.declarations != null) {
            for (DeclarationNode dec : declarations) {
                res += dec.toPrint(indent + "\t");
            }
        }

        if (this.statements != null) {
            for (StatementNode stat : statements) {
                res += stat.toPrint(indent + "\t");
            }
        }

        return res;
    }

    @Override
    public String toString() {
        return toPrint("");
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> err = new ArrayList<SemanticError>();

        env.newScope();

        if (this.declarations != null) {
            for (DeclarationNode dec : declarations) {
                err.addAll(dec.checkSemantics(env));
            }
        }

        if (this.statements != null) {
            for (StatementNode stat : statements) {
                err.addAll(stat.checkSemantics(env));
            }
        }

        env.exitScope();

        return err;
    }

    @Override
    public TypeNode typeCheck() {
        return null;
    }
}
