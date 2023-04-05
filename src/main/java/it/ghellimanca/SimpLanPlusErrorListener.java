package it.ghellimanca;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Handles the error management.
 *
 * @todo: improve underline error for lexical error (not mandatory)
 * @todo: diversify syntactic error - must change the grammar. now the parser only outputs "no viable alternative etc.." errors
 */
public class SimpLanPlusErrorListener extends BaseErrorListener {

    List<String> errors = new ArrayList<>();


    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {

        String finalError = "line " + line + ":" + charPositionInLine + " " + msg + "\n";

        if (recognizer instanceof Parser) {
            String underlineError = underlineError(recognizer, (Token) offendingSymbol, line, charPositionInLine);
            errors.add(finalError + underlineError);
        }
        else {
            errors.add(finalError);
        }

    }
    protected String underlineError(Recognizer recognizer, Token offendingToken, int line, int charPositionInLine) {

        String res = "";
        CommonTokenStream tokens = (CommonTokenStream) recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");
        String errorLine = lines[line - 1];

        res += errorLine + "\n";

        for (int i=0; i<charPositionInLine; i++)
            res += " ";

        int start = offendingToken.getStartIndex();
        int stop = offendingToken.getStopIndex();
        if ( start>=0 && stop>=0 ) {
            for (int i=start; i<=stop; i++)
                res += "^";
        }

        res += "\n";

        return res;
    }
}
