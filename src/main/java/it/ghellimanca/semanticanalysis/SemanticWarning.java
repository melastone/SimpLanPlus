package it.ghellimanca.semanticanalysis;

/**
 * Represents warnings outputted by Node::checkSemantics()
 * Contains a message.
 */
public class SemanticWarning {

    final private String msg;

    public SemanticWarning(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
