package it.ghellimanca.interpreter;

public class Instruction {
    private final String instruction;
    private final String arg1; // either label or register
    private final String arg2;
    private final int offset;
    private final String arg3; // either label or register
    private final int argInt; // to be used with arithmetic instructions

    public Instruction(InstructionBuilder instructionBuilder) {
        this.instruction = instructionBuilder.instruction;
        this.arg1 = instructionBuilder.arg1;
        this.arg2 = instructionBuilder.arg2;
        this.offset = instructionBuilder.offset;
        this.arg3 = instructionBuilder.arg3;
        this.argInt = instructionBuilder.argInt;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public int getOffset() {
        return offset;
    }

    public String getArg3() {
        return arg3;
    }

    public int getArgInt() {
        return argInt;
    }

    //TODO check if it's necessary to initialize the arguments of the builder
    public static class InstructionBuilder {
        private String instruction;
        private String arg1 = null;
        private String arg2 = null;
        private int offset = 0;
        private String arg3 = null;
        private int argInt = 0;

        public InstructionBuilder(String instruction) {
            this.instruction = instruction;
        }

        public InstructionBuilder instruction(String instruction) {
            this.instruction = instruction;
            return this;
        }

        public InstructionBuilder arg1(String arg1) {
            this.arg1 = arg1;
            return this;
        }

        public InstructionBuilder arg2(String arg2) {
            this.arg2 = arg2;
            return this;
        }

        public InstructionBuilder offset(int offset) {
            this.offset = offset;
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

        public Instruction build() {
            return new Instruction(this);
        }
    }
}
