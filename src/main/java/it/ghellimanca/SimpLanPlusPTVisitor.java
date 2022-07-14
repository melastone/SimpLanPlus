package it.ghellimanca;

import it.ghellimanca.ast.exp.*;
import it.ghellimanca.ast.statement.CallNode;
import it.ghellimanca.ast.type.BoolTypeNode;
import it.ghellimanca.ast.type.IntTypeNode;
import it.ghellimanca.ast.type.PointerTypeNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.gen.*;
import it.ghellimanca.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Type;

/**
 *
 * Parse Tree Visitor.
 * Extends SimpLanPlusBaseVisitor, empty implementation of SimpLanPlusVisitor Interface
 *
 */
public class SimpLanPlusPTVisitor extends SimpLanPlusBaseVisitor<Node> {
    @Override
    public Node visitBlock(SimpLanPlusParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public Node visitStatement(SimpLanPlusParser.StatementContext ctx) {
        return super.visitStatement(ctx);
    }

    @Override
    public Node visitDeclaration(SimpLanPlusParser.DeclarationContext ctx) {
        return super.visitDeclaration(ctx);
    }

    @Override
    public Node visitDecFun(SimpLanPlusParser.DecFunContext ctx) {
        return super.visitDecFun(ctx);
    }

    @Override
    public Node visitDecVar(SimpLanPlusParser.DecVarContext ctx) {
        return super.visitDecVar(ctx);
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
    public Node visitAssignment(SimpLanPlusParser.AssignmentContext ctx) {
        return super.visitAssignment(ctx);
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
    public Node visitDeletion(SimpLanPlusParser.DeletionContext ctx) {
        return super.visitDeletion(ctx);
    }

    @Override
    public Node visitPrint(SimpLanPlusParser.PrintContext ctx) {
        return super.visitPrint(ctx);
    }

    @Override
    public Node visitRet(SimpLanPlusParser.RetContext ctx) {
        return super.visitRet(ctx);
    }

    @Override
    public Node visitIte(SimpLanPlusParser.IteContext ctx) {
        return super.visitIte(ctx);
    }

    @Override
    public Node visitCall(SimpLanPlusParser.CallContext ctx) {
        return super.visitCall(ctx);
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
        return super.visit(tree);
    }
}
