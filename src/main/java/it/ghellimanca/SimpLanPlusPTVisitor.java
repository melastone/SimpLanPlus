package it.ghellimanca;

import it.ghellimanca.ast.declaration.DecFunNode;
import it.ghellimanca.ast.declaration.DecVarNode;
import it.ghellimanca.ast.declaration.DeclarationNode;
import it.ghellimanca.ast.exp.*;
import it.ghellimanca.ast.statement.*;
import it.ghellimanca.ast.statement.CallNode;
import it.ghellimanca.ast.type.BoolTypeNode;
import it.ghellimanca.ast.type.IntTypeNode;
import it.ghellimanca.ast.type.PointerTypeNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.gen.*;
import it.ghellimanca.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Parse Tree Visitor.
 *
 * Extends SimpLanPlusBaseVisitor, empty implementation of SimpLanPlusVisitor Interface
 *
 */
public class SimpLanPlusPTVisitor extends SimpLanPlusBaseVisitor<Node> {

    @Override
    public BlockNode visitBlock(SimpLanPlusParser.BlockContext ctx) {

        List<DeclarationNode> declarations = new ArrayList<>();
        List<StatementNode> statements = new ArrayList<>();

        for (SimpLanPlusParser.DeclarationContext declCtx : ctx.declaration()) {
            declarations.add((DeclarationNode) visit(declCtx));
        }

        for (SimpLanPlusParser.StatementContext stmCtx : ctx.statement()){
            statements.add((StatementNode) visit(stmCtx));
        }

        return new BlockNode(declarations,statements);
    }

//    @Override
//    public StatementNode visitStatement(SimpLanPlusParser.StatementContext ctx) {
//        return (StatementNode) visit(ctx);
//    }
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
        else if(ctx.call()!=null){
            res = visit(ctx.call());
        }
        else if(ctx.ite()!=null){
            res = visit(ctx.ite());
        }
        else if(ctx.ret()!=null){
            res = visit(ctx.ret());
        }
        else if(ctx.print()!=null){
            res = visit(ctx.print());
        }
        else if(ctx.deletion()!=null){
            res = visit(ctx.deletion());
        }
        else if(ctx.assignment()!=null){
            res = visit(ctx.assignment());
        }
        else{
            return null;
        }
        return res;
    }

//    @Override
//    public DeclarationNode visitDeclaration(SimpLanPlusParser.DeclarationContext ctx) {
//        return (DeclarationNode) visit(ctx);
//    }

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
            return new DecFunNode("void", id, arguments, body);
        }

    }

    @Override
    public DecVarNode visitDecVar(SimpLanPlusParser.DecVarContext ctx) {

        TypeNode type = (TypeNode) visit(ctx.type());
        IdNode id = new IdNode(ctx.ID().getText());
        ExpNode exp = (ExpNode) visit(ctx.exp());

        return new DecVarNode(type, id, exp);

    }

    /**
     * Creates a node in the AST that represents a 'type', by visiting the parse tree.
     */
    @Override
    public TypeNode visitType(SimpLanPlusParser.TypeContext ctx) {

        String typeText = ctx.getText();

        if (typeText.equals("int")) {
            return new IntTypeNode();
        } else if (typeText.equals("bool")) {
            return new BoolTypeNode();
        }

        return new PointerTypeNode(visitType(ctx.type())); // visiting the TypeNode pointed by the pointer
    }


    @Override
    public ArgNode visitArg(SimpLanPlusParser.ArgContext ctx) {

        TypeNode type = visitType(ctx.type());
        IdNode id = new IdNode(ctx.ID().getText());

        return new ArgNode(type, id);
    }

    @Override
    public AssignmentNode visitAssignment(SimpLanPlusParser.AssignmentContext ctx) {

        LhsNode lhs = visitLhs(ctx.lhs());
        ExpNode exp = (ExpNode) visit(ctx.exp());

        return new AssignmentNode(lhs, exp);
    }

    @Override
    public LhsNode visitLhs(SimpLanPlusParser.LhsContext ctx) {

        if (ctx.lhs() != null) { // lhs '^' case
            LhsNode lhs = visitLhs(ctx.lhs());
            return new LhsNode(lhs.getId(), lhs);
        }
        else { // ID case
            IdNode id = new IdNode(ctx.ID().getText());
            return new LhsNode(id, null);
        }
    }

    @Override
    public DeletionNode visitDeletion(SimpLanPlusParser.DeletionContext ctx) {

        System.out.println("VisitDeletion called..");

        IdNode id = new IdNode(ctx.ID().toString());

        return new DeletionNode(id);
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
        List<StatementNode> statements = new ArrayList<>();

        for (SimpLanPlusParser.StatementContext stmCtx : ctx.statement()){
            statements.add((StatementNode) visit(stmCtx));
        }

        return new IteNode(cond, statements.get(0), statements.get(1));
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

        LhsNode lhs = visitLhs(ctx.lhs());

        return new DerExpNode(lhs);
    }

    @Override
    public NewExpNode visitNewExp(SimpLanPlusParser.NewExpContext ctx) {

        TypeNode type = visitType(ctx.type());

        return new NewExpNode(type);
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

    //todo aggiornare dopo che ho visitCall
    @Override
    public CallExpNode visitCallExp(SimpLanPlusParser.CallExpContext ctx) {
        //CallNode call = visitCall(ctx.call());
        //return new CallExpNode(call);
        return null;
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
