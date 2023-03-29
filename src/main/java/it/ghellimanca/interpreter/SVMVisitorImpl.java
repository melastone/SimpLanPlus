package it.ghellimanca.interpreter;

import it.ghellimanca.gen.svm.SVMBaseVisitor;
import it.ghellimanca.gen.svm.SVMParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SVMVisitorImpl extends SVMBaseVisitor<Void> {

    final private List<InstructionNode> code;

    private final HashMap<String, Integer> labels;
    private final HashMap<Integer, String> labelReferences;

    public SVMVisitorImpl() {
        this.code = new ArrayList<>();
        this.labels = new HashMap<>();
        this.labelReferences = new HashMap<>();
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
        return super.visitAdd(ctx);
    }

    @Override
    public Void visitAddInt(SVMParser.AddIntContext ctx) {
        return super.visitAddInt(ctx);
    }

    @Override
    public Void visitSub(SVMParser.SubContext ctx) {
        return super.visitSub(ctx);
    }

    @Override
    public Void visitSubInt(SVMParser.SubIntContext ctx) {
        return super.visitSubInt(ctx);
    }

    @Override
    public Void visitMult(SVMParser.MultContext ctx) {
        return super.visitMult(ctx);
    }

    @Override
    public Void visitMultInt(SVMParser.MultIntContext ctx) {
        return super.visitMultInt(ctx);
    }

    @Override
    public Void visitDiv(SVMParser.DivContext ctx) {
        return super.visitDiv(ctx);
    }

    @Override
    public Void visitDivInt(SVMParser.DivIntContext ctx) {
        return super.visitDivInt(ctx);
    }

    @Override
    public Void visitAnd(SVMParser.AndContext ctx) {
        return super.visitAnd(ctx);
    }

    @Override
    public Void visitOr(SVMParser.OrContext ctx) {
        return super.visitOr(ctx);
    }

    @Override
    public Void visitNot(SVMParser.NotContext ctx) {
        return super.visitNot(ctx);
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
