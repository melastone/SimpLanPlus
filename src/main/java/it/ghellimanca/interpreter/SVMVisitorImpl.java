package it.ghellimanca.interpreter;

import it.ghellimanca.gen.svm.SVMBaseVisitor;
import it.ghellimanca.gen.svm.SVMParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * SVM grammar Parse Tree Visitor.
 *
 * Creates a list of Instruction Nodes from the Parse Tree generated with Antlr.
 *
 */
public class SVMVisitorImpl extends SVMBaseVisitor<Void> {

    final private List<InstructionNode> code;

    private final HashMap<String, Integer> labels;
    private final HashMap<Integer, String> labelReferences;

    public SVMVisitorImpl() {
        this.code = new ArrayList<>();
        this.labels = new HashMap<>();
        this.labelReferences = new HashMap<>();
    }


    public List<InstructionNode> getCode() {
        return code;
    }


    @Override
    public Void visitAssembly(SVMParser.AssemblyContext ctx) {
        visitChildren(ctx);

        // update jump/branch to label instruction
        // with the actual label address
        for (var labelToJump : labelReferences.entrySet()) {
            Integer codeLine = labelToJump.getKey();
            String label = labelToJump.getValue();
            Integer lineToJump = labels.get(label);

            InstructionNode instructionToModify = code.get(codeLine);
            if (instructionToModify.getInstruction().equals("beq") || instructionToModify.getInstruction().equals("bleq")) {
                code.set(codeLine, new InstructionNode.InstructionBuilder()
                        .instruction(instructionToModify.getInstruction())
                        .arg1(instructionToModify.getArg1())
                        .arg2(instructionToModify.getArg2())
                        .arg3(lineToJump.toString())
                        .build());
            } else {
                // b and jal instructions
                code.set(codeLine, new InstructionNode.InstructionBuilder()
                        .instruction(instructionToModify.getInstruction())
                        .arg1(lineToJump.toString())
                        .build());
            }
        }

        return null;
    }

    @Override
    public Void visitPush(SVMParser.PushContext ctx) {

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("push").arg1(ctx.REG().getText())
                .build());

        return null;
    }

    @Override
    public Void visitPop(SVMParser.PopContext ctx) {

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("pop")
                .build());

        return null;
    }

    @Override
    public Void visitAdd(SVMParser.AddContext ctx) {

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("addi")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return null;
    }

    @Override
    public Void visitAddInt(SVMParser.AddIntContext ctx) {

        code.add(new InstructionNode.InstructionBuilder().instruction("addi")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .argInt(ctx.NUMBER().getText())
                .build());

        return null;
    }

    @Override
    public Void visitSub(SVMParser.SubContext ctx) {

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("sub")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return null;
    }

    @Override
    public Void visitSubInt(SVMParser.SubIntContext ctx) {

        code.add(new InstructionNode.InstructionBuilder().instruction("subi")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .argInt(ctx.NUMBER().getText())
                .build());

        return null;
    }

    @Override
    public Void visitMult(SVMParser.MultContext ctx) {

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("mult")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return null;
    }

    @Override
    public Void visitMultInt(SVMParser.MultIntContext ctx) {

        code.add(new InstructionNode.InstructionBuilder().instruction("multi")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .argInt(ctx.NUMBER().getText())
                .build());

        return null;
    }

    @Override
    public Void visitDiv(SVMParser.DivContext ctx) {

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("div")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return null;
    }

    @Override
    public Void visitDivInt(SVMParser.DivIntContext ctx) {

        code.add(new InstructionNode.InstructionBuilder().instruction("divi")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .argInt(ctx.NUMBER().getText())
                .build());

        return null;
    }

    @Override
    public Void visitAnd(SVMParser.AndContext ctx) {

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("and")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return null;
    }

    @Override
    public Void visitOr(SVMParser.OrContext ctx) {

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("or")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return null;
    }

    @Override
    public Void visitNot(SVMParser.NotContext ctx) {

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("not")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .build());

        return null;
    }

    @Override
    public Void visitLoadWord(SVMParser.LoadWordContext ctx) {
        return super.visitLoadWord(ctx);
    }

    @Override
    public Void visitLoadInteger(SVMParser.LoadIntegerContext ctx) {
        return super.visitLoadInteger(ctx);
    }

    @Override
    public Void visitStoreWord(SVMParser.StoreWordContext ctx) {
        return super.visitStoreWord(ctx);
    }

    @Override
    public Void visitMove(SVMParser.MoveContext ctx) {
        return super.visitMove(ctx);
    }

    @Override
    public Void visitBranchToLabel(SVMParser.BranchToLabelContext ctx) {

        var label = ctx.LABEL().getText();

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("b")
                .arg1(label)
                .build());
        labelReferences.put(code.size(),label);

        return null;
    }

    @Override
    public Void visitBranchIfEqual(SVMParser.BranchIfEqualContext ctx) {

        var label = ctx.LABEL().getText();

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("beq")
                .arg1(ctx.reg1.getText())
                .arg2(ctx.reg2.getText())
                .arg3(label)
                .build());
        labelReferences.put(code.size(),label);

        return null;
    }

    @Override
    public Void visitBranchIfMoreEqual(SVMParser.BranchIfMoreEqualContext ctx) {

        var label = ctx.LABEL().getText();

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("bleq")
                .arg1(ctx.reg1.getText())
                .arg2(ctx.reg2.getText())
                .arg3(label)
                .build());
        labelReferences.put(code.size(),label);

        return null;
    }

    @Override
    public Void visitJumpAndSaveRA(SVMParser.JumpAndSaveRAContext ctx) {

        var label = ctx.LABEL().getText();

        code.add(new InstructionNode.InstructionBuilder()
                .instruction("jal")
                .arg1(label)
                .build());
        labelReferences.put(code.size(),label);

        return null;
    }

    @Override
    public Void visitJumpToRegister(SVMParser.JumpToRegisterContext ctx) {
        return super.visitJumpToRegister(ctx);
    }

    @Override
    public Void visitLabel(SVMParser.LabelContext ctx) {

        labels.put(ctx.LABEL().getText(), code.size());

        return null;
    }

    @Override
    public Void visitHalt(SVMParser.HaltContext ctx) {
        return super.visitHalt(ctx);
    }

    @Override
    public Void visitPrint(SVMParser.PrintContext ctx) {
        return super.visitPrint(ctx);
    }


}
