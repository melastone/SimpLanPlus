package it.ghellimanca.interpreter;

import java.util.*;

public class SVMInterpreter {
    private final List<InstructionNode> code;

    private final Map<String, Integer> registers;

    private int[] memory;

    public SVMInterpreter(List<InstructionNode> code, int memorySize) {
        this.code = code;

        // initializing memory
        this.memory = Arrays.asList(new MemoryCell[memorySize]);

        // initializing registers
        this.registers = new HashMap<>();

        // registers.put()...
    }


    public void run() {

        while ($ip < code.size()) {

            InstructionNode instruction = code.get($ip);
            $ip += 1;

            String arg1 = instruction.getArg1();
            String arg2 = instruction.getArg2();
            String arg3 = instruction.getArg3();
            int offset = instruction.getOffset();
            int argInt = instruction.getArgInt();

            switch (instruction.getOpcode()) {
                case "push":
                    // diminuisco il valore del registro $sp
                    // scrivo in memoria il dato che sto pushando
                    break;
                case "pop":
                    // facoltativo: libera la cella di memoria
                    // diminuisce il valore del registro
                    break;
                case "lw":
                    int srcLw = registers.get(arg2); // prendo indirizzo da registro in arg2

                    registers.put(arg1, readMemory(offset + srcLw)); // prendo valore da memoria a indirizzo src + offset e metto in registro arg1
                    break;
                case "li":
                    registers.put(arg1, argInt); // metto valore in argInt in registro arg1
                    break;
                case "sw":
                    int srcSw = registers.get(arg1);  // prendo valore da mettere in memoria da registro in arg1
                    int destSw = registers.get(arg2); // prendo valore indirizzo memoria da registro in arg2

                    writeMemory(destSw + offset, srcSw); // scrivo in memoria a indirizzo dest + offset il valore
                    break;
                case "mv":
                    int srcMv = registers.get(arg2); // prendo valore da spostare dal registro in arg2

                    registers.put(arg1, srcMv); // metto nel registro in arg1 il valore preso
                    break;
                case "b":
                    $ip = argInt;
                    break;
                case "beq":
                    int reg1Beq = registers.get(arg1); // prendo valore da registro in arg1

                    int reg2Beq = registers.get(arg2);

                    // se i valori sono uguali salto
                    if (reg1Beq == reg2Beq) {
                        $ip = argInt;
                    }
                    break;
                case "bleq":
                    // prendo valore da registro in arg1
                    int reg1Bleq = registers.get(arg1);
                    // prendo valore da registro in arg2
                    int reg2Bleq = registers.get(arg2);

                    // se il primo valore è minore o uguale del secondo salto
                    if (reg1Bleq <= reg2Bleq) {
                        $ip = argInt;
                    }
                    break;
                case "jal":
                    registers.put("$ra", $ip);

                    break;
                case "halt":
                    return;
                default:
                    System.err.println("ERROR: Unrecognized Assembly instruction: " + instruction + ".");
                    return;
            }

        }

    }

    private int readMemory(int address) {
        try {
            return memory[address];
        } catch (IndexOutOfBoundsException e) {
            //throw new MemoryAccessException("Address " + address + " cannot be accessed.");
            return -1;
        }
    }

    private void writeMemory(int address, int data) {
        try {
            memory[address] = data;
        } catch (IndexOutOfBoundsException e) {
            //throw new MemoryAccessException("Address " + address + " cannot be accessed.");
        }
    }
}
