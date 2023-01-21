package it.ghellimanca;

import it.ghellimanca.ast.ProgramNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.gen.SimpLanPlusLexer;
import it.ghellimanca.gen.SimpLanPlusParser;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.MissingInitializationException;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.semanticanalysis.TypeCheckingException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This is the main class.
 * Implements both the compiler and interpreter.
 *
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

        // Creating file object. Deleting errors.txt file if it exists
        File file = new File("errors.txt");
        file.delete();

        /* LEXER */

        // Instantiate the lexer
        SimpLanPlusLexer slpLexer = new SimpLanPlusLexer(CharStreams.fromString(simpLanPlusCode));
        CommonTokenStream slpLexerTokens = new CommonTokenStream(slpLexer);
        SimpLanPlusErrorListener slpErrorListenerLexer = new SimpLanPlusErrorListener();

        slpLexer.removeErrorListeners();

        slpLexer.addErrorListener(slpErrorListenerLexer);


        /* PARSER */

        // Instantiate the parser
        SimpLanPlusParser slpParser = new SimpLanPlusParser(slpLexerTokens);
        SimpLanPlusErrorListener slpErrorListenerParser = new SimpLanPlusErrorListener();

        slpParser.removeErrorListeners();

        slpParser.addErrorListener(slpErrorListenerParser);

        // Creating the tree visitor
        SimpLanPlusPTVisitor parseTreeVisitor = new SimpLanPlusPTVisitor();

        System.out.println("Parsing...");

        // Visiting the tree and generating the AST
        ProgramNode AST = (ProgramNode) parseTreeVisitor.visitProgram(slpParser.program());
//        AST.setMainBlock(); // The main block is special therefore just here a flag is set to signal this


        /* ERROR DETECTION */

        if (slpErrorListenerParser.getErrors().size() > 0 || slpErrorListenerLexer.getErrors().size() > 0) {

            // Creating the file for lexical and syntactic error tracking
            file.createNewFile();

            // Checking for lexical errors
            if (slpErrorListenerLexer.getErrors().size() > 0) {

                // Creating a FileWriter Object
                FileWriter writer = new FileWriter(file, true);
                writer.write("Lexical errors:\n");

                for (String error : slpErrorListenerLexer.getErrors()) {
                    // Writing the content to the file
                    writer.write(error);
                }
                writer.flush();
                writer.close();

                System.err.println("Lexical analysis:");
                System.err.println("There are errors in the file. Look at the errors.txt file.");
            }

            // Checking for syntactical errors
            if (slpErrorListenerParser.getErrors().size() > 0) {

                // Creating a FileWriter Object
                FileWriter writer = new FileWriter(file, true);
                writer.write("Syntactic errors:\n");

                for (String error : slpErrorListenerParser.getErrors()) {
                    // Writing the content to the file
                    writer.write(error);
                }
                writer.write("\n");
                writer.flush();
                writer.close();

                System.err.println("Syntactic analysis:");
                System.err.println("There are errors in the file. Look at the errors.txt file.");
            }

            System.exit(1);
        }


        System.out.println("Parse completed without issues!");
        System.out.println("The AST generated is:" + AST);


        /* SEMANTIC ANALYSIS */

        // Creating the environment
        Environment environment = new Environment();


        // Checking for semantic errors
        try {
            ArrayList<SemanticError> semanticErrors = AST.checkSemantics(environment);

            if (!semanticErrors.isEmpty()) {
                System.err.println("Semantic errors:");

                for (SemanticError err : semanticErrors) {
                    System.err.println(err);
                }

                System.exit(1);
            }
        } catch (MissingInitializationException | ParametersCountException e) {
            System.err.println("Effect analysis error:\n" + e.getMessage());
            System.exit(1);
        }

        // Checking for type errors
        try {
            TypeNode finalType = AST.typeCheck();
            System.out.println("Type checking completed with success!");
            System.out.println("Final block type is " + finalType);
        } catch (TypeCheckingException exception) {
            System.err.println("Type error:\n" + exception.getMessage());
        }


        // Checking for effect analysis errors

        //ArrayList<SemanticError> effectAnalysisErrors = AST.checkEffects(sigma);


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
