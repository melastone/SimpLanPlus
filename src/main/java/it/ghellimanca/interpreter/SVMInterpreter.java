package it.ghellimanca.interpreter;

import java.util.*;

public class SVMInterpreter {

    public static final int CODESIZE = 10000;
    public static final int MEMSIZE = 10000;    //TODO decide if we want to set it from the command line, not necessary to set from command line

    private final List<InstructionNode> code;
    private final int[] memory;

    //private final Map<String, Integer> registers;
    private int $ip;

    private int $sp;
    private int $fp;
    private int $ra, $al, $a0;
    private int $t0, $t1;



    public SVMInterpreter(List<InstructionNode> code) {

        this.code = code;
        this.memory = new int[MEMSIZE];

        this.$ip = 0;

        // initializing registers
        this.$sp = MEMSIZE;
        this.$fp = MEMSIZE;
        //this.$ra, this.$al, this.$a0, this.$t0, this.$t1 = 0;
    }


    private void setRegister(String reg, int value) {
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
            case "t1":
                this.$t1 = value;
                break;
            default:
                // invalid reg name exception
                break;

        }
    }

    private int getRegister(String reg) {
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
            case "t1":
                return this.$t1;
            default:
                // invalid reg name exception
                return -1;

        }
    }


    public void run() {

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
                    // diminuisco il valore del registro $sp
                    // scrivo in memoria il dato che sto pushando
                    break;
                case "pop":
                    // facoltativo: libera la cella di memoria
                    // diminuisce il valore del registro
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
                case "halt":
                    return;
                default:
                    System.err.println("ERROR: Unrecognized Assembly instruction: " + instruction + ".");
                    return;
            }

        }

    }
}
