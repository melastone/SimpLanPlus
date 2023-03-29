// Generated from /home/melania/Documents/UNI-BOH/1 ANNO/CI/progetto/SimpLanPlus/src/main/antlr/SVM.g4 by ANTLR 4.9.2

package it.ghellimanca.gen.svm;
import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, NUMBER=28, REG=29, LABEL=30, WS=31, LINECOMMENTS=32;
	public static final int
		RULE_program = 0, RULE_instruction = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "instruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'push'", "'pop'", "'add'", "'addi'", "'sub'", "'subi'", "'mult'", 
			"'multi'", "'div'", "'divi'", "'and'", "'or'", "'not'", "'lw'", "'('", 
			"')'", "'li'", "'sw'", "'mv'", "'b'", "'beq'", "'bleq'", "'jal'", "'jr'", 
			"':'", "'halt'", "'print'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "NUMBER", "REG", "LABEL", "WS", "LINECOMMENTS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SVMParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__25) | (1L << T__26) | (1L << LABEL))) != 0)) {
				{
				{
				setState(4);
				instruction();
				}
				}
				setState(9);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
	 
		public InstructionContext() { }
		public void copyFrom(InstructionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SubContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public SubContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public MultContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitMult(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultIntContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public MultIntContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitMultInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BranchToLabelContext extends InstructionContext {
		public Token dest;
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public BranchToLabelContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitBranchToLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PopContext extends InstructionContext {
		public PopContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitPop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DivContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public DivContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StoreWordContext extends InstructionContext {
		public Token src;
		public Token offset;
		public Token dest;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public StoreWordContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitStoreWord(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public NotContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LoadWordContext extends InstructionContext {
		public Token dest;
		public Token offset;
		public Token src;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public LoadWordContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitLoadWord(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubIntContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public SubIntContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitSubInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BranchIfMoreEqualContext extends InstructionContext {
		public Token reg1;
		public Token reg2;
		public Token dest;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public BranchIfMoreEqualContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitBranchIfMoreEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public AndContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LoadIntegerContext extends InstructionContext {
		public Token dest;
		public Token val;
		public TerminalNode REG() { return getToken(SVMParser.REG, 0); }
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public LoadIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitLoadInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public AddContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoveContext extends InstructionContext {
		public Token dest;
		public Token src;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public MoveContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitMove(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public OrContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpAndSaveRAContext extends InstructionContext {
		public Token dest;
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public JumpAndSaveRAContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitJumpAndSaveRA(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpToRegisterContext extends InstructionContext {
		public Token dest;
		public TerminalNode REG() { return getToken(SVMParser.REG, 0); }
		public JumpToRegisterContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitJumpToRegister(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LabelContext extends InstructionContext {
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public LabelContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PushContext extends InstructionContext {
		public Token src;
		public TerminalNode REG() { return getToken(SVMParser.REG, 0); }
		public PushContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitPush(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class HaltContext extends InstructionContext {
		public HaltContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitHalt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintContext extends InstructionContext {
		public Token src;
		public TerminalNode REG() { return getToken(SVMParser.REG, 0); }
		public PrintContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BranchIfEqualContext extends InstructionContext {
		public Token reg1;
		public Token reg2;
		public Token dest;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public BranchIfEqualContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitBranchIfEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddIntContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public AddIntContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAddInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DivIntContext extends InstructionContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public DivIntContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitDivInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new PushContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				match(T__0);
				setState(11);
				((PushContext)_localctx).src = match(REG);
				}
				break;
			case T__1:
				_localctx = new PopContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(12);
				match(T__1);
				}
				break;
			case T__2:
				_localctx = new AddContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(13);
				match(T__2);
				setState(14);
				((AddContext)_localctx).dest = match(REG);
				setState(15);
				((AddContext)_localctx).reg1 = match(REG);
				setState(16);
				((AddContext)_localctx).reg2 = match(REG);
				}
				break;
			case T__3:
				_localctx = new AddIntContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(17);
				match(T__3);
				setState(18);
				((AddIntContext)_localctx).dest = match(REG);
				setState(19);
				((AddIntContext)_localctx).reg1 = match(REG);
				setState(20);
				((AddIntContext)_localctx).val = match(NUMBER);
				}
				break;
			case T__4:
				_localctx = new SubContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(21);
				match(T__4);
				setState(22);
				((SubContext)_localctx).dest = match(REG);
				setState(23);
				((SubContext)_localctx).reg1 = match(REG);
				setState(24);
				((SubContext)_localctx).reg2 = match(REG);
				}
				break;
			case T__5:
				_localctx = new SubIntContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(25);
				match(T__5);
				setState(26);
				((SubIntContext)_localctx).dest = match(REG);
				setState(27);
				((SubIntContext)_localctx).reg1 = match(REG);
				setState(28);
				((SubIntContext)_localctx).val = match(NUMBER);
				}
				break;
			case T__6:
				_localctx = new MultContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(29);
				match(T__6);
				setState(30);
				((MultContext)_localctx).dest = match(REG);
				setState(31);
				((MultContext)_localctx).reg1 = match(REG);
				setState(32);
				((MultContext)_localctx).reg2 = match(REG);
				}
				break;
			case T__7:
				_localctx = new MultIntContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(33);
				match(T__7);
				setState(34);
				((MultIntContext)_localctx).dest = match(REG);
				setState(35);
				((MultIntContext)_localctx).reg1 = match(REG);
				setState(36);
				((MultIntContext)_localctx).val = match(NUMBER);
				}
				break;
			case T__8:
				_localctx = new DivContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(37);
				match(T__8);
				setState(38);
				((DivContext)_localctx).dest = match(REG);
				setState(39);
				((DivContext)_localctx).reg1 = match(REG);
				setState(40);
				((DivContext)_localctx).reg2 = match(REG);
				}
				break;
			case T__9:
				_localctx = new DivIntContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(41);
				match(T__9);
				setState(42);
				((DivIntContext)_localctx).dest = match(REG);
				setState(43);
				((DivIntContext)_localctx).reg1 = match(REG);
				setState(44);
				((DivIntContext)_localctx).val = match(NUMBER);
				}
				break;
			case T__10:
				_localctx = new AndContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(45);
				match(T__10);
				setState(46);
				((AndContext)_localctx).dest = match(REG);
				setState(47);
				((AndContext)_localctx).reg1 = match(REG);
				setState(48);
				((AndContext)_localctx).reg2 = match(REG);
				}
				break;
			case T__11:
				_localctx = new OrContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(49);
				match(T__11);
				setState(50);
				((OrContext)_localctx).dest = match(REG);
				setState(51);
				((OrContext)_localctx).reg1 = match(REG);
				setState(52);
				((OrContext)_localctx).reg2 = match(REG);
				}
				break;
			case T__12:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(53);
				match(T__12);
				setState(54);
				((NotContext)_localctx).dest = match(REG);
				setState(55);
				((NotContext)_localctx).reg1 = match(REG);
				}
				break;
			case T__13:
				_localctx = new LoadWordContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(56);
				match(T__13);
				setState(57);
				((LoadWordContext)_localctx).dest = match(REG);
				setState(58);
				((LoadWordContext)_localctx).offset = match(NUMBER);
				setState(59);
				match(T__14);
				setState(60);
				((LoadWordContext)_localctx).src = match(REG);
				setState(61);
				match(T__15);
				}
				break;
			case T__16:
				_localctx = new LoadIntegerContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(62);
				match(T__16);
				setState(63);
				((LoadIntegerContext)_localctx).dest = match(REG);
				setState(64);
				((LoadIntegerContext)_localctx).val = match(NUMBER);
				}
				break;
			case T__17:
				_localctx = new StoreWordContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(65);
				match(T__17);
				setState(66);
				((StoreWordContext)_localctx).src = match(REG);
				setState(67);
				((StoreWordContext)_localctx).offset = match(NUMBER);
				setState(68);
				match(T__14);
				setState(69);
				((StoreWordContext)_localctx).dest = match(REG);
				setState(70);
				match(T__15);
				}
				break;
			case T__18:
				_localctx = new MoveContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(71);
				match(T__18);
				setState(72);
				((MoveContext)_localctx).dest = match(REG);
				setState(73);
				((MoveContext)_localctx).src = match(REG);
				}
				break;
			case T__19:
				_localctx = new BranchToLabelContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(74);
				match(T__19);
				setState(75);
				((BranchToLabelContext)_localctx).dest = match(LABEL);
				}
				break;
			case T__20:
				_localctx = new BranchIfEqualContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(76);
				match(T__20);
				setState(77);
				((BranchIfEqualContext)_localctx).reg1 = match(REG);
				setState(78);
				((BranchIfEqualContext)_localctx).reg2 = match(REG);
				setState(79);
				((BranchIfEqualContext)_localctx).dest = match(LABEL);
				}
				break;
			case T__21:
				_localctx = new BranchIfMoreEqualContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(80);
				match(T__21);
				setState(81);
				((BranchIfMoreEqualContext)_localctx).reg1 = match(REG);
				setState(82);
				((BranchIfMoreEqualContext)_localctx).reg2 = match(REG);
				setState(83);
				((BranchIfMoreEqualContext)_localctx).dest = match(LABEL);
				}
				break;
			case T__22:
				_localctx = new JumpAndSaveRAContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(84);
				match(T__22);
				setState(85);
				((JumpAndSaveRAContext)_localctx).dest = match(LABEL);
				}
				break;
			case T__23:
				_localctx = new JumpToRegisterContext(_localctx);
				enterOuterAlt(_localctx, 22);
				{
				setState(86);
				match(T__23);
				setState(87);
				((JumpToRegisterContext)_localctx).dest = match(REG);
				}
				break;
			case LABEL:
				_localctx = new LabelContext(_localctx);
				enterOuterAlt(_localctx, 23);
				{
				setState(88);
				match(LABEL);
				setState(89);
				match(T__24);
				}
				break;
			case T__25:
				_localctx = new HaltContext(_localctx);
				enterOuterAlt(_localctx, 24);
				{
				setState(90);
				match(T__25);
				}
				break;
			case T__26:
				_localctx = new PrintContext(_localctx);
				enterOuterAlt(_localctx, 25);
				{
				setState(91);
				match(T__26);
				setState(92);
				((PrintContext)_localctx).src = match(REG);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"b\4\2\t\2\4\3\t"+
		"\3\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\5\3`\n\3\3\3\2\2\4\2\4\2\2\2x\2\t\3\2\2\2\4_\3\2\2\2"+
		"\6\b\5\4\3\2\7\6\3\2\2\2\b\13\3\2\2\2\t\7\3\2\2\2\t\n\3\2\2\2\n\3\3\2"+
		"\2\2\13\t\3\2\2\2\f\r\7\3\2\2\r`\7\37\2\2\16`\7\4\2\2\17\20\7\5\2\2\20"+
		"\21\7\37\2\2\21\22\7\37\2\2\22`\7\37\2\2\23\24\7\6\2\2\24\25\7\37\2\2"+
		"\25\26\7\37\2\2\26`\7\36\2\2\27\30\7\7\2\2\30\31\7\37\2\2\31\32\7\37\2"+
		"\2\32`\7\37\2\2\33\34\7\b\2\2\34\35\7\37\2\2\35\36\7\37\2\2\36`\7\36\2"+
		"\2\37 \7\t\2\2 !\7\37\2\2!\"\7\37\2\2\"`\7\37\2\2#$\7\n\2\2$%\7\37\2\2"+
		"%&\7\37\2\2&`\7\36\2\2\'(\7\13\2\2()\7\37\2\2)*\7\37\2\2*`\7\37\2\2+,"+
		"\7\f\2\2,-\7\37\2\2-.\7\37\2\2.`\7\36\2\2/\60\7\r\2\2\60\61\7\37\2\2\61"+
		"\62\7\37\2\2\62`\7\37\2\2\63\64\7\16\2\2\64\65\7\37\2\2\65\66\7\37\2\2"+
		"\66`\7\37\2\2\678\7\17\2\289\7\37\2\29`\7\37\2\2:;\7\20\2\2;<\7\37\2\2"+
		"<=\7\36\2\2=>\7\21\2\2>?\7\37\2\2?`\7\22\2\2@A\7\23\2\2AB\7\37\2\2B`\7"+
		"\36\2\2CD\7\24\2\2DE\7\37\2\2EF\7\36\2\2FG\7\21\2\2GH\7\37\2\2H`\7\22"+
		"\2\2IJ\7\25\2\2JK\7\37\2\2K`\7\37\2\2LM\7\26\2\2M`\7 \2\2NO\7\27\2\2O"+
		"P\7\37\2\2PQ\7\37\2\2Q`\7 \2\2RS\7\30\2\2ST\7\37\2\2TU\7\37\2\2U`\7 \2"+
		"\2VW\7\31\2\2W`\7 \2\2XY\7\32\2\2Y`\7\37\2\2Z[\7 \2\2[`\7\33\2\2\\`\7"+
		"\34\2\2]^\7\35\2\2^`\7\37\2\2_\f\3\2\2\2_\16\3\2\2\2_\17\3\2\2\2_\23\3"+
		"\2\2\2_\27\3\2\2\2_\33\3\2\2\2_\37\3\2\2\2_#\3\2\2\2_\'\3\2\2\2_+\3\2"+
		"\2\2_/\3\2\2\2_\63\3\2\2\2_\67\3\2\2\2_:\3\2\2\2_@\3\2\2\2_C\3\2\2\2_"+
		"I\3\2\2\2_L\3\2\2\2_N\3\2\2\2_R\3\2\2\2_V\3\2\2\2_X\3\2\2\2_Z\3\2\2\2"+
		"_\\\3\2\2\2_]\3\2\2\2`\5\3\2\2\2\4\t_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}