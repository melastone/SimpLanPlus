package it.ghellimanca.interpreter;

import java.util.*;

public class SVMInterpreter {
    private final List<Instruction> code;

    private final Map<String, Integer> registers;

    private final List<MemoryCell> memory; //TODO decide if it's best as a list or normal array

    private final int memorySize; //TODO decide if we want to set it from the command line, not necessary to set from command line

    public SVMInterpreter(List<Instruction> code, int memorySize) {
        this.code = code;
        this.memorySize = memorySize;

        // initializing memory
        this.memory = Arrays.asList(new MemoryCell[memorySize]);

        // initializing registers
        this.registers = new HashMap<>();

        // registers.put()...
    }


    public void run() {

    }
}
