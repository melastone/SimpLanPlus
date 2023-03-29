package it.ghellimanca.interpreter;

import java.util.*;

public class SVMInterpreter {

    public static final int CODESIZE = 10000;
    public static final int MEMSIZE = 10000;    //TODO decide if we want to set it from the command line, not necessary to set from command line

    private final List<InstructionNode> code;
    private final int[] memory;

    private final Map<String, Integer> registers;
    private int $ip;



    public SVMInterpreter(List<InstructionNode> code) {

        this.code = code;
        this.memory = new int[MEMSIZE];

        this.registers = new HashMap<>();
        this.$ip = 0;

        // initializing registers
        registers.put("$sp", MEMSIZE);
        registers.put("$fp", MEMSIZE);
        registers.put("$ra", null);
        registers.put("$al", null);
        registers.put("$a0", null);
        registers.put("$t0", null);
        registers.put("$t1", null);
    }


    public void run() {

        while ($ip < code.size()) {

            InstructionNode instruction = code.get($ip);
            $ip += 1;

            switch (instruction.getOpcode()) {
                case "push":
                    // diminuisco il valore del registro $sp
                    // scrivo in memoria il dato che sto pushando
                    break;
                case "pop":
                    // facoltativo: libera la cella di memoria
                    // diminuisce il valore del registro
                case "halt":
                    return;
                default:
                    System.err.println("ERROR: Unrecognized Assembly instruction: " + instruction + ".");
                    return;
            }

        }

    }
}
