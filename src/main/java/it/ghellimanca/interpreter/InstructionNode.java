package it.ghellimanca.interpreter;

/**
 * This class represents an Instruction Node.
 *
 * Instruction Nodes are the products of code generation. Each node
 * corresponds to a single assembly instruction.
 * Instruction Nodes will be the basic builing blocks for code after
 * the Parse Tree visit.
 *
 */
public class InstructionNode {

    private final String opCode;
    private final String arg1; // either label or register
    private final int offset;
    private final String arg2;
    private final String arg3; // either label or register
    private final int argInt; // to be used with arithmetic instructions



    public InstructionNode(InstructionBuilder instructionBuilder) {
        this.opCode = instructionBuilder.opCode;
        this.arg1 = instructionBuilder.arg1;
        this.offset = instructionBuilder.offset;
        this.arg2 = instructionBuilder.arg2;
        this.arg3 = instructionBuilder.arg3;
        this.argInt = instructionBuilder.argInt;
    }



    public String getOpcode() {
        return opCode;
    }

    public String getArg1() {
        return arg1;
    }

    public int getOffset() {
        return offset;
    }

    public String getArg2() {
        return arg2;
    }

    public String getArg3() {
        return arg3;
    }

    public int getArgInt() {
        return argInt;
    }


    @Override
    public String toString() {
        return "SVMInstruction{" +
                "opCode='" + opCode + '\'' +
                ", arg1='" + arg1 + '\'' +
                ", offset=" + offset +
                ", arg2='" + arg2 + '\'' +
                ", arg3='" + arg3 + '\'' +
                ", argInt='" + argInt + '\'' +
                "}\n";
    }


    public static class InstructionBuilder {
        private String opCode;
        private String arg1 = null;
        private int offset = 0;
        private String arg2 = null;
        private String arg3 = null;
        private int argInt = 0;


        public InstructionBuilder opCode(String instruction) {
            this.opCode = instruction;
            return this;
        }

        public InstructionBuilder arg1(String arg1) {
            this.arg1 = arg1;
            return this;
        }

        public InstructionBuilder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public InstructionBuilder offset(String offset) {
            this.offset = Integer.parseInt(offset);
            return this;
        }

        public InstructionBuilder arg2(String arg2) {
            this.arg2 = arg2;
            return this;
        }

        public InstructionBuilder arg3(String arg3) {
            this.arg3 = arg3;
            return this;
        }

        public InstructionBuilder argInt(int argInt) {
            this.argInt = argInt;
            return this;
        }

        public InstructionBuilder argInt(String argInt) {
            this.argInt = Integer.parseInt(argInt);
            return this;
        }

        public InstructionNode build() {
            return new InstructionNode(this);
        }
    }
}
