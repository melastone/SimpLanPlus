package it.ghellimanca;

import it.ghellimanca.ast.declaration.DecFunNode;
import it.ghellimanca.ast.declaration.DecVarNode;
import it.ghellimanca.ast.declaration.DeclarationNode;
import it.ghellimanca.ast.exp.*;
import it.ghellimanca.ast.statement.*;
import it.ghellimanca.ast.statement.CallNode;
import it.ghellimanca.ast.type.*;
import it.ghellimanca.ast.*;
import it.ghellimanca.gen.simplanplus.SimpLanPlusBaseVisitor;
import it.ghellimanca.gen.simplanplus.SimpLanPlusParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Parse Tree Visitor.
 *
 * Extends SimpLanPlusBaseVisitor, empty implementation of SimpLanPlusVisitor Interface
 *
 */
public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {


    @Override
    public Node visitProgram(SimpLanPlusParser.ProgramContext ctx) {

        List<DeclarationNode> declarations = new ArrayList<>();
        List<StatementNode> statements = new ArrayList<>();

        for (SimpLanPlusParser.DeclarationContext declCtx : ctx.declaration()) {
            declarations.add((DeclarationNode) visit(declCtx));
        }

        for (SimpLanPlusParser.StatementContext stmCtx : ctx.statement()){
            statements.add((StatementNode) visit(stmCtx));
        }

        return new ProgramNode(declarations,statements);
    }


    @Override
    public BlockNode visitBlock(SimpLanPlusParser.BlockContext ctx) {

        List<DecVarNode> variableDeclarations = new ArrayList<>();
        List<StatementNode> statements = new ArrayList<>();

        for (SimpLanPlusParser.DecVarContext declCtx : ctx.decVar()) {
            variableDeclarations.add((DecVarNode) visit(declCtx));
        }

        for (SimpLanPlusParser.StatementContext stmCtx : ctx.statement()){
            statements.add((StatementNode) visit(stmCtx));
        }

        return new BlockNode(variableDeclarations,statements);
    }


    @Override
    public Node visitDeclaration(SimpLanPlusParser.DeclarationContext ctx){
        Node res;

        if(ctx.decFun() != null) {
            res = visit(ctx.decFun());
        }
        else if(ctx.decVar() != null){
            res = visit(ctx.decVar());
        }
        else {
            return null;
        }

        return res;
    }


    @Override public Node visitStatement(SimpLanPlusParser.StatementContext ctx){
        Node res;

        if (ctx.block() != null) {
            res = visit(ctx.block());
        }
        else if(ctx.call() != null){
            res = visit(ctx.call());
        }
        else if(ctx.ite() != null){
            res = visit(ctx.ite());
        }
        else if(ctx.ret() != null){
            res = visit(ctx.ret());
        }
        else if(ctx.print() != null){
            res = visit(ctx.print());
        }
        else if(ctx.assignment() != null){
            res = visit(ctx.assignment());
        }
        else {
            return null;
        }

        return res;
    }


    @Override
    public Node visitDecFun(SimpLanPlusParser.DecFunContext ctx) {

        IdNode id = new IdNode(ctx.ID().getText());
        List<ArgNode> arguments = new ArrayList<>();    // can be null in case fun has no params
        BlockNode body = (BlockNode) visit(ctx.block());

        for (SimpLanPlusParser.ArgContext argCtx : ctx.arg()) {
            arguments.add((ArgNode) visit(argCtx));
        }

        if (ctx.type() != null) {
            TypeNode type = visitType(ctx.type());
            return new DecFunNode(type, id, arguments, body);
        } else {
            VoidTypeNode type = new VoidTypeNode();
            return new DecFunNode(type, id, arguments, body);
        }

    }


    @Override
    public DecVarNode visitDecVar(SimpLanPlusParser.DecVarContext ctx) {

        TypeNode type = (TypeNode) visit(ctx.type());
        IdNode id = new IdNode(ctx.ID().getText());
        ExpNode exp = (ExpNode) visit(ctx.exp());

        return new DecVarNode(type, id, exp);

    }


    @Override
    public TypeNode visitType(SimpLanPlusParser.TypeContext ctx) {

        String typeText = ctx.getText();

        if (typeText.equals("int")) {
            return new IntTypeNode();
        } else if (typeText.equals("bool")) {
            return new BoolTypeNode();
        }

        return new VoidTypeNode();
    }


    @Override
    public ArgNode visitArg(SimpLanPlusParser.ArgContext ctx) {

        TypeNode type = visitType(ctx.type());
        IdNode id = new IdNode(ctx.ID().getText());

        if(ctx.children.get(0).toString().equals("var")) {
            VarTypeNode varType = new VarTypeNode(type);
            return new ArgNode(varType, id);
        } else {
            return new ArgNode(type, id);
        }
    }


    @Override
    public AssignmentNode visitAssignment(SimpLanPlusParser.AssignmentContext ctx) {

        IdNode id = new IdNode(ctx.ID().getText());
        ExpNode exp = (ExpNode) visit(ctx.exp());

        return new AssignmentNode(id, exp);
    }


    @Override
    public PrintNode visitPrint(SimpLanPlusParser.PrintContext ctx) {

        ExpNode exp = (ExpNode) visit(ctx.exp());

        return new PrintNode(exp);
    }


    @Override
    public ReturnNode visitRet(SimpLanPlusParser.RetContext ctx) {

        ExpNode exp = (ExpNode) visit(ctx.exp());

        return new ReturnNode(exp);
    }


    @Override
    public IteNode visitIte(SimpLanPlusParser.IteContext ctx) {

        ExpNode cond = (ExpNode) visit(ctx.exp());

        List<SimpLanPlusParser.StatementContext> statementsCtx = ctx.statement();

        if (statementsCtx.size() > 1) {
            List<StatementNode> statements = new ArrayList<>();

            for (SimpLanPlusParser.StatementContext stmCtx : ctx.statement()){
                statements.add((StatementNode) visit(stmCtx));
            }

            return new IteNode(cond, statements.get(0), statements.get(1));
        }

        return new IteNode(cond,(StatementNode) visit(statementsCtx.get(0)));
    }


    @Override
    public CallNode visitCall(SimpLanPlusParser.CallContext ctx) {

        IdNode id = new IdNode(ctx.ID().getText());
        List<ExpNode> parameters = new ArrayList<>();

        for (SimpLanPlusParser.ExpContext expCtx : ctx.exp()){
            parameters.add((ExpNode) visit(expCtx));
        }

        return new CallNode(id, parameters);

    }


    @Override
    public BaseExpNode visitBaseExp(SimpLanPlusParser.BaseExpContext ctx) {

        ExpNode exp = (ExpNode) visit(ctx.exp());

        return new BaseExpNode(exp);
    }


    @Override
    public BinExpNode visitBinExp(SimpLanPlusParser.BinExpContext ctx) {

        ExpNode left = (ExpNode) visit(ctx.left);
        String op = ctx.op.getText();
        ExpNode right = (ExpNode) visit(ctx.right);

        return new BinExpNode(left, op, right);
    }


    @Override
    public DerExpNode visitDerExp(SimpLanPlusParser.DerExpContext ctx) {

        IdNode id = new IdNode(ctx.ID().getText());

        return new DerExpNode(id);
    }


    @Override
    public ValExpNode visitValExp(SimpLanPlusParser.ValExpContext ctx) {

        int number = Integer.parseInt(ctx.NUMBER().getText());

        return new ValExpNode(number);
    }


    @Override
    public NegExpNode visitNegExp(SimpLanPlusParser.NegExpContext ctx) {

        ExpNode exp = (ExpNode) visit(ctx.exp());

        return new NegExpNode(exp);
    }


    @Override
    public BoolExpNode visitBoolExp(SimpLanPlusParser.BoolExpContext ctx) {

        boolean bool = Boolean.parseBoolean(ctx.getText());

        return new BoolExpNode(bool);
    }


    @Override
    public CallExpNode visitCallExp(SimpLanPlusParser.CallExpContext ctx) {
        CallNode call = visitCall(ctx.call());
        return new CallExpNode(call);
    }


    @Override
    public NotExpNode visitNotExp(SimpLanPlusParser.NotExpContext ctx) {
        ExpNode exp = (ExpNode) visit(ctx.exp());

        return new NotExpNode(exp);
    }


    @Override
    public Node visit(ParseTree tree) {
        return tree != null ? super.visit(tree) : null;

    }
}
