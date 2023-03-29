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
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("add")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitAddInt(SVMParser.AddIntContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder().instruction("addi")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.NUMBER().getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitSub(SVMParser.SubContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("sub")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitSubInt(SVMParser.SubIntContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder().instruction("subi")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.NUMBER().getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitMult(SVMParser.MultContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("mult")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitMultInt(SVMParser.MultIntContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder().instruction("multi")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.NUMBER().getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitDiv(SVMParser.DivContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("div")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitDivInt(SVMParser.DivIntContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder().instruction("divi")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.NUMBER().getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitAnd(SVMParser.AndContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("and")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitOr(SVMParser.OrContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("or")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .arg3(ctx.reg2.getText())
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitNot(SVMParser.NotContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("not")
                .arg1(ctx.dest.getText())
                .arg2(ctx.reg1.getText())
                .build());

        return code;
    }

    //------------------------------------------------------------------------------------------------
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
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("halt")
                .build());

        return code;
    }

    @Override
    public List<Instruction> visitPrint(SVMParser.PrintContext ctx) {
        List<Instruction> code = new ArrayList<>();

        code.add(new Instruction.InstructionBuilder()
                .instruction("print")
                .arg1(ctx.REG().getText())
                .build());

        return code;
    }


}
