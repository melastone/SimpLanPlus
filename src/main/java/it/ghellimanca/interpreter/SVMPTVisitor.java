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
    public List<Instruction> visitAddInt(SVMParser.AddIntContext ctx) {
        return super.visitAddInt(ctx);
    }

    @Override
    public List<Instruction> visitSub(SVMParser.SubContext ctx) {
        return super.visitSub(ctx);
    }

    @Override
    public List<Instruction> visitSubInt(SVMParser.SubIntContext ctx) {
        return super.visitSubInt(ctx);
    }

    @Override
    public List<Instruction> visitMult(SVMParser.MultContext ctx) {
        return super.visitMult(ctx);
    }

    @Override
    public List<Instruction> visitMultInt(SVMParser.MultIntContext ctx) {
        return super.visitMultInt(ctx);
    }

    @Override
    public List<Instruction> visitDiv(SVMParser.DivContext ctx) {
        return super.visitDiv(ctx);
    }

    @Override
    public List<Instruction> visitDivInt(SVMParser.DivIntContext ctx) {
        return super.visitDivInt(ctx);
    }

    @Override
    public List<Instruction> visitAnd(SVMParser.AndContext ctx) {
        return super.visitAnd(ctx);
    }

    @Override
    public List<Instruction> visitOr(SVMParser.OrContext ctx) {
        return super.visitOr(ctx);
    }

    @Override
    public List<Instruction> visitNot(SVMParser.NotContext ctx) {
        return super.visitNot(ctx);
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
    public List<Instruction> visitBranchIfEqual(SVMParser.BranchIfEqualContext ctx) {
        return super.visitBranchIfEqual(ctx);
    }

    @Override
    public List<Instruction> visitBranchIfMoreEqual(SVMParser.BranchIfMoreEqualContext ctx) {
        return super.visitBranchIfMoreEqual(ctx);
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


}
