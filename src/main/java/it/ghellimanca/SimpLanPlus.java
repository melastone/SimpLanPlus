package it.ghellimanca;

import it.ghellimanca.ast.ProgramNode;
import it.ghellimanca.gen.simplanplus.SimpLanPlusLexer;
import it.ghellimanca.gen.simplanplus.SimpLanPlusParser;
import it.ghellimanca.gen.svm.SVMLexer;
import it.ghellimanca.gen.svm.SVMParser;
import it.ghellimanca.interpreter.exception.MemoryAccessException;
import it.ghellimanca.interpreter.SVMInterpreter;
import it.ghellimanca.interpreter.SVMVisitorImpl;
import it.ghellimanca.interpreter.exception.AssemblyInstructionException;
import it.ghellimanca.interpreter.exception.SmallCodeSizeException;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.errors.*;
import it.ghellimanca.semanticanalysis.errors.TypeCheckingException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * This is the main class.
 * Implements both the compiler and interpreter.
 *
 * todo: stampare gli errori lessicali/sintattici presenti nell'assembly
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

        // Creating file object. Deleting errors.txt file if it exists
        if (filename.indexOf(".") > 0) {
            filename = filename.substring(0, filename.lastIndexOf("."));
        }
        File file = new File(filename + ".svm");

        file.delete();
        file.createNewFile();

        FileWriter writer = new FileWriter(file, true);
        writer.write(assembly);

        writer.flush();
        writer.close();

        /* INTERPRETER */
        run(assembly);

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
        SimpLanPlusVisitorImpl parseTreeVisitor = new SimpLanPlusVisitorImpl();

        System.out.println("Parsing...");

        // Visiting the tree and generating the AST
        ProgramNode AST = (ProgramNode) parseTreeVisitor.visitProgram(slpParser.program());

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


        System.out.println("Parse completed without issues.");
        // System.out.println("The AST generated is:" + AST);


        /* SEMANTIC ANALYSIS */

        // Creating the environment
        Environment environment = new Environment();

        System.out.println("Semantic Analysis...");


        // Checking for semantic and effect analysis errors
        try {
            ArrayList<SemanticWarning> semanticWarnings = AST.checkSemantics(environment);

            if (!semanticWarnings.isEmpty()) {
                System.err.println("Warnings:");

                for (SemanticWarning warning : semanticWarnings) {
                    System.err.println(warning);
                }
            }

        } catch (MultipleDeclarationException | MissingDeclarationException e) {
            System.err.println("Semantic analysis error:\n" + e.getMessage());
            System.exit(1);
        } catch (MissingInitializationException | ParametersException e) {
            System.err.println("Effect analysis error:\n" + e.getMessage());
            System.exit(1);
        }

        System.out.println("Semantic analysis (hopefully) completed.");


        // Checking for type errors

        System.out.println("Type checking...");

        try {
            AST.typeCheck();
        } catch (TypeCheckingException exception) {
            System.err.println("Type checking error:\n" + exception.getMessage());
            System.exit(1);
        }

        System.out.println("Type checking completed with success!");


        /* CODE GENERATION */

        System.out.println("Assembling...");

        return AST.codeGeneration();

    }

    /**
     * Handles the interpretation phase
     */
    private static void run(String assemblyCode) {


        /* VM LEXER */

        // Instantiate the lexer
        SVMLexer svmLexer = new SVMLexer(CharStreams.fromString(assemblyCode));
        CommonTokenStream svmLexerTokens = new CommonTokenStream(svmLexer);

        SimpLanPlusErrorListener svmErrorListenerLexer = new SimpLanPlusErrorListener();
        svmLexer.removeErrorListeners();
        svmLexer.addErrorListener(svmErrorListenerLexer);

        // Checking for lexical errors.
        if (svmErrorListenerLexer.getErrors().size() > 0) {
            System.err.println("There are lexical errors in the generated Assembly code. It cannot compile.");
            System.exit(1);
        }


        /* VM PARSER */

        SVMParser svmParser = new SVMParser(svmLexerTokens);

        SimpLanPlusErrorListener svmErrorListenerParser = new SimpLanPlusErrorListener();
        svmParser.removeErrorListeners();
        svmParser.addErrorListener(svmErrorListenerParser);

         // Visiting the tree and generating the AST.
        SVMVisitorImpl svmVisitor = new SVMVisitorImpl();
        svmVisitor.visit(svmParser.assembly());


        /* VM INTERPRETER */

        try {
            SVMInterpreter svmInterpreter = new SVMInterpreter(svmVisitor.getCode());
            System.out.println(svmInterpreter.getCode());
            System.out.println("Program output:");
            svmInterpreter.run();
        } catch (AssemblyInstructionException | SmallCodeSizeException | MemoryAccessException exc) {
            System.err.println("Error: " + exc.getMessage());
            System.exit(1);
        }


    }

    static final class NonExistentInputFileException extends Exception {

        private static final long serialVersionUID = 8745799711138778410L;

        public NonExistentInputFileException(String message) {
            super(message);
        }
    }
}
