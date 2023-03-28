// Generated from /home/melania/Documents/UNI-BOH/1 ANNO/CI/progetto/SimpLanPlus/src/main/antlr/SVM.g4 by ANTLR 4.9.2

package it.ghellimanca.gen.svm;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SVMParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SVMVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SVMParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SVMParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pushIntoStack}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPushIntoStack(SVMParser.PushIntoStackContext ctx);
	/**
	 * Visit a parse tree produced by the {@code popFromStack}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopFromStack(SVMParser.PopFromStackContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sum}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSum(SVMParser.SumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addInteger}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddInteger(SVMParser.AddIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subtraction}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtraction(SVMParser.SubtractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subInt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubInt(SVMParser.SubIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loadWord}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadWord(SVMParser.LoadWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loadInteger}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadInteger(SVMParser.LoadIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code storeWord}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoreWord(SVMParser.StoreWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code move}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(SVMParser.MoveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code branchToLabel}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchToLabel(SVMParser.BranchToLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code branchIfEq}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchIfEq(SVMParser.BranchIfEqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpAndSaveRA}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpAndSaveRA(SVMParser.JumpAndSaveRAContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpToRegister}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpToRegister(SVMParser.JumpToRegisterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code label}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(SVMParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code halt}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHalt(SVMParser.HaltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code print}
	 * labeled alternative in {@link SVMParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(SVMParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#push}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(SVMParser.PushContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#pop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(SVMParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(SVMParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#addi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddi(SVMParser.AddiContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#sub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(SVMParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#subi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubi(SVMParser.SubiContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#lw}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLw(SVMParser.LwContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#li}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLi(SVMParser.LiContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#sw}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSw(SVMParser.SwContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#mv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMv(SVMParser.MvContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB(SVMParser.BContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#beq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeq(SVMParser.BeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#jal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJal(SVMParser.JalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#jr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJr(SVMParser.JrContext ctx);
}