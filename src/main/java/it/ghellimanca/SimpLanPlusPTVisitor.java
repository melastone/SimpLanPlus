package it.ghellimanca;

import it.ghellimanca.ast.declaration.DecVarNode;
import it.ghellimanca.ast.declaration.DeclarationNode;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.statement.DeletionNode;
import it.ghellimanca.ast.statement.StatementNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.gen.*;
import it.ghellimanca.ast.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Parse Tree Visitor.
 *
 * Extends SimpLanPlusBaseVisitor, empty implementation of SimpLanPlusVisitor Interface
 * @todo: manage ctx -> Node conversion
 * @todo: manage terminalNode -> IdNode conversion
 *
 */
public class SimpLanPlusPTVisitor extends SimpLanPlusBaseVisitor<Node> {

    @Override
    public BlockNode visitBlock(SimpLanPlusParser.BlockContext ctx) {

        List<DeclarationNode> declarations = new ArrayList<>();
        List<StatementNode> statements = new ArrayList<>();

        for (SimpLanPlusParser.DeclarationContext declCtx : ctx.declaration()) {
//            declarations.add((DeclarationNode) declCtx.toNode())
        }

        for (SimpLanPlusParser.StatementContext stmCtx : ctx.statement()){
//            statements.add((StatementNode) stmCtx.toNode())
        }

        return new BlockNode(declarations,statements);
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
    public DecVarNode visitDecVar(SimpLanPlusParser.DecVarContext ctx) {

//        TypeNode type = ctx.type().toNode()
//        IdNode id = ctx.ID().fromTerminalNodeToIdNode()
//        ExpNode = ctx.exp().toNode()

        return new DecVarNode(null,null);
    }

    @Override
    public Node visitType(SimpLanPlusParser.TypeContext ctx) {
        return super.visitType(ctx);
    }

    @Override
    public Node visitArg(SimpLanPlusParser.ArgContext ctx) {
        return super.visitArg(ctx);
    }

    @Override
    public Node visitAssignment(SimpLanPlusParser.AssignmentContext ctx) {
        return super.visitAssignment(ctx);
    }

    @Override
    public Node visitLhs(SimpLanPlusParser.LhsContext ctx) {
        return super.visitLhs(ctx);
    }

    @Override
    public DeletionNode visitDeletion(SimpLanPlusParser.DeletionContext ctx) {

        IdNode id = new IdNode(ctx.ID().toString());

        return new DeletionNode(id);
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
    public Node visitBaseExp(SimpLanPlusParser.BaseExpContext ctx) {
        return super.visitBaseExp(ctx);
    }

    @Override
    public Node visitBinExp(SimpLanPlusParser.BinExpContext ctx) {
        return super.visitBinExp(ctx);
    }

    @Override
    public Node visitDerExp(SimpLanPlusParser.DerExpContext ctx) {
        return super.visitDerExp(ctx);
    }

    @Override
    public Node visitNewExp(SimpLanPlusParser.NewExpContext ctx) {
        return super.visitNewExp(ctx);
    }

    @Override
    public Node visitValExp(SimpLanPlusParser.ValExpContext ctx) {
        return super.visitValExp(ctx);
    }

    @Override
    public Node visitNegExp(SimpLanPlusParser.NegExpContext ctx) {
        return super.visitNegExp(ctx);
    }

    @Override
    public Node visitBoolExp(SimpLanPlusParser.BoolExpContext ctx) {
        return super.visitBoolExp(ctx);
    }

    @Override
    public Node visitCallExp(SimpLanPlusParser.CallExpContext ctx) {
        return super.visitCallExp(ctx);
    }

    @Override
    public Node visitNotExp(SimpLanPlusParser.NotExpContext ctx) {
        return super.visitNotExp(ctx);
    }

    @Override
    public Node visit(ParseTree tree) {
        return super.visit(tree);
    }
}
