package it.ghellimanca.ast;

import it.ghellimanca.Environment;
import it.ghellimanca.SemanticError;

import java.util.ArrayList;

//generic node of the ast
public interface Node {

    // check for errors in the node
    ArrayList<SemanticError> checkSemantics(Environment env);

    // check effects analysis errors

    // type checking
    Node typeCheck();
}
