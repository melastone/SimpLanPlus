package it.ghellimanca.ast.declaration;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;
import it.ghellimanca.ast.ArgNode;
import it.ghellimanca.ast.BlockNode;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Node of the AST for a function declaration.
 *
 * Has two attributes, which are mutually exclusive:
 * type is used for int/bool function
 * voidType is used in case the function is void.
 */
public class DecFunNode implements Node {

    final private TypeNode type;
    final private String voidType;
    final private IdNode id;
    final private List<ArgNode> arguments;
    final private BlockNode body;


    /*
    Constructor for int/bool functions
     */
    public DecFunNode(TypeNode type, IdNode id, List<ArgNode> arguments, BlockNode body) {

        this.type = type;
        this.voidType = null;
        this.id = id;
        this.arguments = arguments;
        this.body = body;
    }

    /*
    Constructor for void functions
     */
    public DecFunNode(String voidType, IdNode id, List<ArgNode> arguments, BlockNode body) {
        this.type = null;
        this.voidType = voidType;
        this.id = id;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "DECFUN" + type.toPrint(indent + "\t") + id.toPrint(indent + "\t");
        if (this.arguments != null) {
            for (ArgNode a : arguments) {
                res += a.toPrint(indent + "\t");
            }
        }
        res += body.toPrint(indent + "\t");
        return res;
     }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck() {
        return null;
    }
}
