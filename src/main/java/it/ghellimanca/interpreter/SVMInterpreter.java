package it.ghellimanca.interpreter;

import it.ghellimanca.interpreter.exception.AssemblyInstructionException;
import it.ghellimanca.interpreter.exception.MemoryAccessException;
import it.ghellimanca.interpreter.exception.SmallCodeSizeException;

import java.util.*;


/**
 * Interpreter for SVM assembly code.
 *
 */
public class SVMInterpreter {
    public static final int CODESIZE = 10000;
    public static final int MEMSIZE = 10000;

    private final List<InstructionNode> code;
    private final int[] memory;

    private int $ip;

    private int $sp;
    private int $fp;
    private int $ra, $al, $a0;
    private int $t0, $t1;


    /**
     * Constructor for {@code SVMIntepreter}.
     *
     * @param code      the assembly code, translated into a List of Instruction Nodes
     * @throws SmallCodeSizeException   in case the given code has higher dimension than the allocated space
     */
    public SVMInterpreter(List<InstructionNode> code) throws SmallCodeSizeException {

        if (code.size() > CODESIZE) {
            throw new SmallCodeSizeException("Compiled assemply code size " + code.size() + " bigger than available codesie " + CODESIZE);
        }

        this.code = code;
        this.memory = new int[MEMSIZE];

        this.$ip = 0;

        // initializing registers
        this.$sp = MEMSIZE;
        this.$fp = MEMSIZE;
    }

    /**
     * Getter for the {@code code} attribute.
     *
     * @return the assembly code which is a List of Instruction Nodes
     */
    public List<InstructionNode> getCode() {
        return code;
    }

    /**
     * Sets the value of a given register.
     *
     * @param reg the string representing the register we want to set
     * @param value the integer value we want to give to the register, could be either an address or a value
     * @throws AssemblyInstructionException in case an unknown register string is passed
     */
    private void setRegister(String reg, int value) throws AssemblyInstructionException {
        switch (reg) {
            case "$sp":
                this.$sp = value;
                break;
            case "$fp":
                this.$fp = value;
                break;
            case "$ra":
                this.$ra = value;
                break;
            case "$al":
                this.$al = value;
                break;
            case "$a0":
                this.$a0 = value;
                break;
            case "$t0":
                this.$t0 = value;
                break;
            case "$t1":
                this.$t1 = value;
                break;
            default:
                throw new AssemblyInstructionException("Unrecognized Assembly instruction; " + reg + " register unknown.");

        }
    }

    /**
     * Gets the value of a given register.
     *
     * @param reg the string representing the register we want to get the value of
     * @throws AssemblyInstructionException in case an unknown register string is passed
     */
    private int getRegister(String reg) throws AssemblyInstructionException {
        switch (reg) {
            case "$sp":
                return this.$sp;
            case "$fp":
                return this.$fp;
            case "$ra":
                return this.$ra;
            case "$al":
                return this.$al;
            case "$a0":
                return this.$a0;
            case "$t0":
                return this.$t0;
            case "$t1":
                return this.$t1;
            default:
                throw new AssemblyInstructionException("Unrecognized Assembly instruction; " + reg + " register unknown.");

        }
    }


    /**
     * Executes the list of instructions generated from the code generation.
     *
     * @throws MemoryAccessException in case of going over the limit of the memory with some register
     * @throws AssemblyInstructionException in case an unknown instruction is being executed
     */
    public void run() throws MemoryAccessException, AssemblyInstructionException {

        while ($ip < code.size()) {

            InstructionNode instruction = code.get($ip);
            $ip += 1;

            var arg1 = instruction.getArg1();
            var arg2 = instruction.getArg2();
            var offset = instruction.getOffset();
            var arg3 = instruction.getArg3();
            var argInt = instruction.getArgInt();

            switch (instruction.getOpcode()) {
                case "push":
                    setRegister("$sp", getRegister("$sp") - 1);
                    writeMemory(getRegister("$sp"), getRegister(arg1));
                    break;
                case "pop":
                    setRegister("$sp", getRegister("$sp") + 1);
                    break;
                case "add":
                    setRegister(arg1, getRegister(arg2) + getRegister(arg3));
                    break;
                case "addi":
                    setRegister(arg1, getRegister(arg2) + argInt);
                    break;
                case "sub":
                    setRegister(arg1, getRegister(arg2) - getRegister(arg3));
                    break;
                case "subi":
                    setRegister(arg1, getRegister(arg2) - argInt);
                    break;
                case "mult":
                    setRegister(arg1, getRegister(arg2) * getRegister(arg3));
                    break;
                case "multi":
                    setRegister(arg1, getRegister(arg2) * argInt);
                    break;
                case "div":
                    setRegister(arg1, getRegister(arg2) / getRegister(arg3));
                    break;
                case "divi":
                    setRegister(arg1, getRegister(arg2) / argInt);
                    break;
                case "and": {
                    boolean bArg2 = getRegister(arg2) == 1;
                    boolean bArg3 = getRegister(arg3) == 1;
                    setRegister(arg1, bArg2 && bArg3 ? 1 : 0);
                    break;}
                case "or":{
                    boolean bArg2 = getRegister(arg2) == 1;
                    boolean bArg3 = getRegister(arg3) == 1;
                    setRegister(arg1, bArg2 || bArg3 ? 1 : 0);
                    break;}
                case "not":
                    setRegister(arg1, getRegister(arg2) == 1 ? 0 : 1);
                    break;
                case "lw":
                    int srcLw = getRegister(arg2);
                    setRegister(arg1, readMemory(offset + srcLw));
                    break;
                case "li":
                    setRegister(arg1, argInt);
                    break;
                case "sw":
                    int srcSw = getRegister(arg1);
                    int destSw = getRegister(arg2);
                    writeMemory(destSw + offset, srcSw);
                    break;
                case "mv":
                    int srcMv = getRegister(arg2);
                    setRegister(arg1, srcMv);
                    break;
                case "b":
                    $ip = argInt;
                    break;
                case "beq":
                    int reg1Beq = getRegister(arg1);

                    int reg2Beq = getRegister(arg2);

                    if (reg1Beq == reg2Beq) {
                        $ip = argInt;
                    }
                    break;
                case "bleq":
                    int reg1Bleq = getRegister(arg1);
                    int reg2Bleq = getRegister(arg2);

                    if (reg1Bleq >= reg2Bleq) {
                        $ip = argInt;
                    }
                    break;
                case "jal":
                    setRegister("$ra", $ip);
                    $ip = argInt;
                    break;
                case "jr":
                    $ip = getRegister(arg1);
                    break;
                case "print":
                    System.out.println("Printing: " + getRegister(arg1)+"\n");
                    break;
                case "halt":
                    return;
                default:
                    throw new AssemblyInstructionException("Unrecognized Assembly instruction: " + instruction + " ; opCode unknown.");
            }

        }

    }

    /**
     * Reads the value of a cell in a given address.
     *
     * @param address the address of the memory cell we want to read
     * @return the value inside the cell at the given address
     * @throws MemoryAccessException in case an address bigger than the size of the memory is given
     */
    private int readMemory(int address) throws MemoryAccessException {
        try {
            return memory[address];
        } catch (IndexOutOfBoundsException e) {
            throw new MemoryAccessException("Reached maximum memory, reading.");
        }
    }


    /**
     * Writes a value inside a memory cell of a given address.
     *
     * @param address the address of the memory cell we want to write
     * @param data the value we want to write int the cell at the given address
     * @throws MemoryAccessException in case an address bigger than the size of the memory is given
     */
    private void writeMemory(int address, int data) throws MemoryAccessException {
        try {
            memory[address] = data;
        } catch (IndexOutOfBoundsException e) {
            throw new MemoryAccessException("Reached maximum memory, writing.");
        }
    }
}
