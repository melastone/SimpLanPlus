package it.ghellimanca;

/**
 * Represents error outputted by Node::checkSemantics()
 * Contains a message
 */
public class SemanticError {

    final private String msg;

    public SemanticError(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
