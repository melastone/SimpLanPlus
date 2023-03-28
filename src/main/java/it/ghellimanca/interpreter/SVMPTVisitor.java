package it.ghellimanca.interpreter;

import it.ghellimanca.gen.svm.SVMBaseVisitor;
import it.ghellimanca.gen.svm.SVMParser;

import java.util.ArrayList;
import java.util.List;

public class SVMPTVisitor extends SVMBaseVisitor<List<Instruction>> {

    @Override
    public List<Instruction> visitProgram(SVMParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }

    @Override
    public List<Instruction> visitPushIntoStack(SVMParser.PushIntoStackContext ctx) {
        return super.visitPushIntoStack(ctx);
    }

    @Override
    public List<Instruction> visitPopFromStack(SVMParser.PopFromStackContext ctx) {
        return super.visitPopFromStack(ctx);
    }

    @Override
    public List<Instruction> visitSum(SVMParser.SumContext ctx) {
        return super.visitSum(ctx);
    }

    @Override
    public List<Instruction> visitAddInteger(SVMParser.AddIntegerContext ctx) {
        return super.visitAddInteger(ctx);
    }

    @Override
    public List<Instruction> visitSubtraction(SVMParser.SubtractionContext ctx) {
        return super.visitSubtraction(ctx);
    }

    @Override
    public List<Instruction> visitSubInt(SVMParser.SubIntContext ctx) {
        return super.visitSubInt(ctx);
    }

    @Override
    public List<Instruction> visitLoadWord(SVMParser.LoadWordContext ctx) {
        return super.visitLoadWord(ctx);
    }

    @Override
    public List<Instruction> visitLoadInteger(SVMParser.LoadIntegerContext ctx) {
        return super.visitLoadInteger(ctx);
    }

    @Override
    public List<Instruction> visitStoreWord(SVMParser.StoreWordContext ctx) {
        return super.visitStoreWord(ctx);
    }

    @Override
    public List<Instruction> visitMove(SVMParser.MoveContext ctx) {
        return super.visitMove(ctx);
    }

    @Override
    public List<Instruction> visitBranchToLabel(SVMParser.BranchToLabelContext ctx) {
        return super.visitBranchToLabel(ctx);
    }

    @Override
    public List<Instruction> visitBranchIfEq(SVMParser.BranchIfEqContext ctx) {
        return super.visitBranchIfEq(ctx);
    }

    @Override
    public List<Instruction> visitJumpAndSaveRA(SVMParser.JumpAndSaveRAContext ctx) {
        return super.visitJumpAndSaveRA(ctx);
    }

    @Override
    public List<Instruction> visitJumpToRegister(SVMParser.JumpToRegisterContext ctx) {
        return super.visitJumpToRegister(ctx);
    }

    @Override
    public List<Instruction> visitLabel(SVMParser.LabelContext ctx) {
        return super.visitLabel(ctx);
    }

    @Override
    public List<Instruction> visitHalt(SVMParser.HaltContext ctx) {
        return super.visitHalt(ctx);
    }

    @Override
    public List<Instruction> visitPrint(SVMParser.PrintContext ctx) {
        return super.visitPrint(ctx);
    }

    @Override
    public List<Instruction> visitPush(SVMParser.PushContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
        .instruction("push").arg1(ctx.REG().getText())
        .build());

        return code;
    }

    @Override
    public List<Instruction> visitPop(SVMParser.PopContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("pop")
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitAdd(SVMParser.AddContext ctx) {
        return super.visitAdd(ctx);
    }

    @Override
    public List<Instruction> visitAddi(SVMParser.AddiContext ctx) {
        return super.visitAddi(ctx);
    }

    @Override
    public List<Instruction> visitSub(SVMParser.SubContext ctx) {
        return super.visitSub(ctx);
    }

    @Override
    public List<Instruction> visitSubi(SVMParser.SubiContext ctx) {
        return super.visitSubi(ctx);
    }

    @Override
    public List<Instruction> visitLw(SVMParser.LwContext ctx) {
        return super.visitLw(ctx);
    }

    @Override
    public List<Instruction> visitLi(SVMParser.LiContext ctx) {
        return super.visitLi(ctx);
    }

    @Override
    public List<Instruction> visitSw(SVMParser.SwContext ctx) {
        return super.visitSw(ctx);
    }

    @Override
    public List<Instruction> visitMv(SVMParser.MvContext ctx) {
        return super.visitMv(ctx);
    }

    @Override
    public List<Instruction> visitB(SVMParser.BContext ctx) {
        return super.visitB(ctx);
    }

    @Override
    public List<Instruction> visitBeq(SVMParser.BeqContext ctx) {
        return super.visitBeq(ctx);
    }

    @Override
    public List<Instruction> visitJal(SVMParser.JalContext ctx) {
        return super.visitJal(ctx);
    }

    @Override
    public List<Instruction> visitJr(SVMParser.JrContext ctx) {
        return super.visitJr(ctx);
    }
}
