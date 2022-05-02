package it.ghellimanca;

import it.ghellimanca.ast.BlockNode;
import it.ghellimanca.gen.SimpLanPlusLexer;
import it.ghellimanca.gen.SimpLanPlusParser;
import it.ghellimanca.SimpLanPlusPTVisitor
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is the main class.
 * Implements both the compiler and interpreter.
 *
 * @todo: create verbose listener
 * @todo: implement the tree visitor
 */

public class SimpLanPlus {

    public static void main(String[] args) throws NonExistentInputFileException, IOException {

        String filename = args[0];

        if (!Paths.get(filename).toFile().exists()) {
            throw new NonExistentInputFileException("Input file " + filename + " does not exist.");
        }

        String fileContent = Files.readString(Paths.get(filename));

        System.out.println("Input file:\t" + filename);

        /* COMPILER */
        var assembly = compile(fileContent);
    }



    /**
     * Handles the compilation phase
     */
    private static String compile(final String simpLanPlusCode) {


        /* LEXER */

        // Creating the lexer
        SimpLanPlusLexer slpLexer = new SimpLanPlusLexer(CharStreams.fromString(simpLanPlusCode));
        CommonTokenStream slpLexerTokens = new CommonTokenStream(slpLexer);

        slpLexer.removeErrorListeners();
        //slpLexer.addErrorListener(new VerboseListener());
        
        // Checking for lexical errors
        // @todo: understand Antlr Lexer error management, then implement errorCount()
//        if (slpLexer.errorCount() > 0) {
//            System.err.println("Lexical analysis:");
//            System.err.println("There are lexical errors in the file. It cannot compile.");
//            System.exit(1);
//        }


        /* PARSER */

        // Creating the parser
        SimpLanPlusParser slpParser = new SimpLanPlusParser(slpLexerTokens);

//        slpParser.removeErrorListeners();
//        slpParser.addErrorListener(new VerboseListener());

        // Creating the tree visitor
        SimpLanPlusPTVisitor parseTreeVisitor = new SimpLanPlusPTVisitor();

        // Visiting the tree and generating the AST
        BlockNode AST = (BlockNode) parseTreeVisitor.visitBlock(slpParser.block());
        //AST.setMainBlock(); // The main block is special therefore just here a flag is set to signal this
        // (it's important for the code generation).

        // Checking for syntactical errors
        /*
        *
        * Il paster implementa la classe Parser, che ha un attributo _syntaxError che tiene il conto degli errori fatti
        *
        * */
        if (slpParser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Syntactic analysis:");
            System.err.println("There are syntactical errors in the file, look above.");
            System.exit(1);
        }


        /* SEMANTIC ANALYSIS */

        // Creating the environment

        // Checking for semantic errors

        // Checking for type errors

        // Checking for effect analysis errors


        /* CODE GENERATION */
    }

    /**
     * Handles the interpretation phase
     */
    private static void run() {

    }

    static final class NonExistentInputFileException extends Exception {

        private static final long serialVersionUID = 8745799711138778410L;

        public NonExistentInputFileException(String message) {
            super(message);
        }
    }
}
