package it.ghellimanca;

import it.ghellimanca.ast.BlockNode;
import it.ghellimanca.gen.SimpLanPlusLexer;
import it.ghellimanca.gen.SimpLanPlusParser;
import it.ghellimanca.SimpLanPlusPTVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is the main class.
 * Implements both the compiler and interpreter.
 *
 * @todo: create verbose listener
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
    private static String compile(final String simpLanPlusCode) throws IOException {
        File file = new File("errors.txt");

        // creates the file
        if(!file.createNewFile()) {
            if(file.delete())
                file.createNewFile();
        }

        /* LEXER */

        // Creating the lexer
        SimpLanPlusLexer slpLexer = new SimpLanPlusLexer(CharStreams.fromString(simpLanPlusCode));
        CommonTokenStream slpLexerTokens = new CommonTokenStream(slpLexer);
        SimpLanPlusErrorListener slpErrorListenerLexer = new SimpLanPlusErrorListener();

        slpLexer.removeErrorListeners();

        // Adding our error listener
        slpLexer.addErrorListener(slpErrorListenerLexer);

        
        // Checking for lexical errors
        // @todo: understand Antlr Lexer error management, then implement errorCount()
       /* if (slpLexer.errorCount() > 0) {
            System.err.println("Lexical analysis:");
            System.err.println("There are lexical errors in the file. It cannot compile.");
            System.exit(1);
        }*/


        /* PARSER */

        // Creating the parser
        SimpLanPlusParser slpParser = new SimpLanPlusParser(slpLexerTokens);
        SimpLanPlusErrorListener slpErrorListenerParser = new SimpLanPlusErrorListener();

        slpParser.removeErrorListeners();

        // Adding our error listener
        slpParser.addErrorListener(slpErrorListenerParser);

        // Creating the tree visitor
        SimpLanPlusPTVisitor parseTreeVisitor = new SimpLanPlusPTVisitor();

        System.out.println("Parsing...");

        // Visiting the tree and generating the AST
        BlockNode AST = (BlockNode) parseTreeVisitor.visitBlock(slpParser.block());
//        AST.setMainBlock(); // The main block is special therefore just here a flag is set to signal this


        // Checking for syntactical errors
        /*
        *
        * Il paster implementa la classe Parser, che ha un attributo _syntaxError che tiene il conto degli errori fatti
        *
        * */
        //if (slpParser.getNumberOfSyntaxErrors() > 0) {
        if (slpErrorListenerLexer.getErrors().size() > 0) {
            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file, true);
            writer.write("Lexical errors:\n");
            for (String error : slpErrorListenerLexer.getErrors()) {
                // Writes the content to the file
                writer.write(error);
            }
            writer.flush();
            writer.close();

            System.err.println("Lexical analysis:");
            System.err.println("There are errors in the file. Look at the errors.txt file.");
            //System.exit(0);
        }
        if (slpErrorListenerParser.getErrors().size() > 0) {
            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file, true);
            writer.write("Syntactic errors:\n");
            for (String error : slpErrorListenerParser.getErrors()) {
                // Writes the content to the file
                writer.write(error);
            }
            writer.write("\n");
            writer.flush();
            writer.close();

            System.err.println("Syntactic analysis:");
            System.err.println("There are errors in the file. Look at the errors.txt file.");
            //System.exit(0);
        }

        if (slpErrorListenerParser.getErrors().size() > 0 || slpErrorListenerLexer.getErrors().size() > 0) {
            System.exit(0);
        }


        System.out.println("Parse completed without issues!");
        System.out.println("The AST generated is:" + AST);


        /* SEMANTIC ANALYSIS */

        // Creating the environment

        // Checking for semantic errors

        // Checking for type errors

        // Checking for effect analysis errors


        /* CODE GENERATION */

        return null;

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
