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
		T__17=18, T__18=19, NUMBER=20, REG=21, LABEL=22, WS=23, LINECOMMENTS=24;
	public static final int
		RULE_program = 0, RULE_instruction = 1, RULE_push = 2, RULE_pop = 3, RULE_add = 4, 
		RULE_addi = 5, RULE_sub = 6, RULE_subi = 7, RULE_lw = 8, RULE_li = 9, 
		RULE_sw = 10, RULE_mv = 11, RULE_b = 12, RULE_beq = 13, RULE_jal = 14, 
		RULE_jr = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "instruction", "push", "pop", "add", "addi", "sub", "subi", 
			"lw", "li", "sw", "mv", "b", "beq", "jal", "jr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'halt'", "'print'", "'push'", "'pop'", "'add'", "'addi'", 
			"'sub'", "'subi'", "'lw'", "'('", "')'", "'li'", "'sw'", "'mv'", "'b'", 
			"'beq'", "'jal'", "'jr'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "NUMBER", "REG", "LABEL", 
			"WS", "LINECOMMENTS"
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
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << LABEL))) != 0)) {
				{
				{
				setState(32);
				instruction();
				}
				}
				setState(37);
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
	public static class MoveContext extends InstructionContext {
		public MvContext mv() {
			return getRuleContext(MvContext.class,0);
		}
		public MoveContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitMove(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpAndSaveRAContext extends InstructionContext {
		public JalContext jal() {
			return getRuleContext(JalContext.class,0);
		}
		public JumpAndSaveRAContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitJumpAndSaveRA(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JumpToRegisterContext extends InstructionContext {
		public JrContext jr() {
			return getRuleContext(JrContext.class,0);
		}
		public JumpToRegisterContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitJumpToRegister(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubtractionContext extends InstructionContext {
		public SubContext sub() {
			return getRuleContext(SubContext.class,0);
		}
		public SubtractionContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitSubtraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BranchIfEqContext extends InstructionContext {
		public BeqContext beq() {
			return getRuleContext(BeqContext.class,0);
		}
		public BranchIfEqContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitBranchIfEq(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SumContext extends InstructionContext {
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public SumContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitSum(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BranchToLabelContext extends InstructionContext {
		public BContext b() {
			return getRuleContext(BContext.class,0);
		}
		public BranchToLabelContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitBranchToLabel(this);
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
	public static class AddIntegerContext extends InstructionContext {
		public AddiContext addi() {
			return getRuleContext(AddiContext.class,0);
		}
		public AddIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAddInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PushIntoStackContext extends InstructionContext {
		public PushContext push() {
			return getRuleContext(PushContext.class,0);
		}
		public PushIntoStackContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitPushIntoStack(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StoreWordContext extends InstructionContext {
		public SwContext sw() {
			return getRuleContext(SwContext.class,0);
		}
		public StoreWordContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitStoreWord(this);
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
	public static class LoadWordContext extends InstructionContext {
		public LwContext lw() {
			return getRuleContext(LwContext.class,0);
		}
		public LoadWordContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitLoadWord(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubIntContext extends InstructionContext {
		public SubiContext subi() {
			return getRuleContext(SubiContext.class,0);
		}
		public SubIntContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitSubInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PopFromStackContext extends InstructionContext {
		public PopContext pop() {
			return getRuleContext(PopContext.class,0);
		}
		public PopFromStackContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitPopFromStack(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LoadIntegerContext extends InstructionContext {
		public LiContext li() {
			return getRuleContext(LiContext.class,0);
		}
		public LoadIntegerContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitLoadInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				_localctx = new PushIntoStackContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				push();
				}
				break;
			case T__4:
				_localctx = new PopFromStackContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				pop();
				}
				break;
			case T__5:
				_localctx = new SumContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				add();
				}
				break;
			case T__6:
				_localctx = new AddIntegerContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(41);
				addi();
				}
				break;
			case T__7:
				_localctx = new SubtractionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(42);
				sub();
				}
				break;
			case T__8:
				_localctx = new SubIntContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(43);
				subi();
				}
				break;
			case T__9:
				_localctx = new LoadWordContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(44);
				lw();
				}
				break;
			case T__12:
				_localctx = new LoadIntegerContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(45);
				li();
				}
				break;
			case T__13:
				_localctx = new StoreWordContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(46);
				sw();
				}
				break;
			case T__14:
				_localctx = new MoveContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(47);
				mv();
				}
				break;
			case T__15:
				_localctx = new BranchToLabelContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(48);
				b();
				}
				break;
			case T__16:
				_localctx = new BranchIfEqContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(49);
				beq();
				}
				break;
			case T__17:
				_localctx = new JumpAndSaveRAContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(50);
				jal();
				}
				break;
			case T__18:
				_localctx = new JumpToRegisterContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(51);
				jr();
				}
				break;
			case LABEL:
				_localctx = new LabelContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(52);
				match(LABEL);
				setState(53);
				match(T__0);
				}
				break;
			case T__1:
				_localctx = new HaltContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(54);
				match(T__1);
				}
				break;
			case T__2:
				_localctx = new PrintContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(55);
				match(T__2);
				setState(56);
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

	public static class PushContext extends ParserRuleContext {
		public Token src;
		public TerminalNode REG() { return getToken(SVMParser.REG, 0); }
		public PushContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_push; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitPush(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PushContext push() throws RecognitionException {
		PushContext _localctx = new PushContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_push);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(T__3);
			setState(60);
			((PushContext)_localctx).src = match(REG);
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

	public static class PopContext extends ParserRuleContext {
		public PopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitPop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PopContext pop() throws RecognitionException {
		PopContext _localctx = new PopContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_pop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(T__4);
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

	public static class AddContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		AddContext _localctx = new AddContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_add);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__5);
			setState(65);
			((AddContext)_localctx).dest = match(REG);
			setState(66);
			((AddContext)_localctx).reg1 = match(REG);
			setState(67);
			((AddContext)_localctx).reg2 = match(REG);
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

	public static class AddiContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public AddiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addi; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAddi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddiContext addi() throws RecognitionException {
		AddiContext _localctx = new AddiContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_addi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__6);
			setState(70);
			((AddiContext)_localctx).dest = match(REG);
			setState(71);
			((AddiContext)_localctx).reg1 = match(REG);
			setState(72);
			((AddiContext)_localctx).val = match(NUMBER);
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

	public static class SubContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token reg2;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public SubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sub; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitSub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubContext sub() throws RecognitionException {
		SubContext _localctx = new SubContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sub);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__7);
			setState(75);
			((SubContext)_localctx).dest = match(REG);
			setState(76);
			((SubContext)_localctx).reg1 = match(REG);
			setState(77);
			((SubContext)_localctx).reg2 = match(REG);
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

	public static class SubiContext extends ParserRuleContext {
		public Token dest;
		public Token reg1;
		public Token val;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public SubiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subi; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitSubi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubiContext subi() throws RecognitionException {
		SubiContext _localctx = new SubiContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_subi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__8);
			setState(80);
			((SubiContext)_localctx).dest = match(REG);
			setState(81);
			((SubiContext)_localctx).reg1 = match(REG);
			setState(82);
			((SubiContext)_localctx).val = match(NUMBER);
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

	public static class LwContext extends ParserRuleContext {
		public Token dest;
		public Token offset;
		public Token src;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public LwContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lw; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitLw(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LwContext lw() throws RecognitionException {
		LwContext _localctx = new LwContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_lw);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__9);
			setState(85);
			((LwContext)_localctx).dest = match(REG);
			setState(86);
			((LwContext)_localctx).offset = match(NUMBER);
			setState(87);
			match(T__10);
			setState(88);
			((LwContext)_localctx).src = match(REG);
			setState(89);
			match(T__11);
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

	public static class LiContext extends ParserRuleContext {
		public Token dest;
		public Token val;
		public TerminalNode REG() { return getToken(SVMParser.REG, 0); }
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public LiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_li; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitLi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiContext li() throws RecognitionException {
		LiContext _localctx = new LiContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_li);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(T__12);
			setState(92);
			((LiContext)_localctx).dest = match(REG);
			setState(93);
			((LiContext)_localctx).val = match(NUMBER);
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

	public static class SwContext extends ParserRuleContext {
		public Token src;
		public Token offset;
		public Token dest;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public SwContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sw; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitSw(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwContext sw() throws RecognitionException {
		SwContext _localctx = new SwContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_sw);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__13);
			setState(96);
			((SwContext)_localctx).src = match(REG);
			setState(97);
			((SwContext)_localctx).offset = match(NUMBER);
			setState(98);
			match(T__10);
			setState(99);
			((SwContext)_localctx).dest = match(REG);
			setState(100);
			match(T__11);
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

	public static class MvContext extends ParserRuleContext {
		public Token dest;
		public Token src;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public MvContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mv; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitMv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MvContext mv() throws RecognitionException {
		MvContext _localctx = new MvContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_mv);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__14);
			setState(103);
			((MvContext)_localctx).dest = match(REG);
			setState(104);
			((MvContext)_localctx).src = match(REG);
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

	public static class BContext extends ParserRuleContext {
		public Token dest;
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public BContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitB(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BContext b() throws RecognitionException {
		BContext _localctx = new BContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_b);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__15);
			setState(107);
			((BContext)_localctx).dest = match(LABEL);
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

	public static class BeqContext extends ParserRuleContext {
		public Token reg1;
		public Token reg2;
		public Token dest;
		public List<TerminalNode> REG() { return getTokens(SVMParser.REG); }
		public TerminalNode REG(int i) {
			return getToken(SVMParser.REG, i);
		}
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public BeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beq; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitBeq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeqContext beq() throws RecognitionException {
		BeqContext _localctx = new BeqContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_beq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__16);
			setState(110);
			((BeqContext)_localctx).reg1 = match(REG);
			setState(111);
			((BeqContext)_localctx).reg2 = match(REG);
			setState(112);
			((BeqContext)_localctx).dest = match(LABEL);
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

	public static class JalContext extends ParserRuleContext {
		public Token dest;
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public JalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitJal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JalContext jal() throws RecognitionException {
		JalContext _localctx = new JalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_jal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(T__17);
			setState(115);
			((JalContext)_localctx).dest = match(LABEL);
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

	public static class JrContext extends ParserRuleContext {
		public Token dest;
		public TerminalNode REG() { return getToken(SVMParser.REG, 0); }
		public JrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitJr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JrContext jr() throws RecognitionException {
		JrContext _localctx = new JrContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_jr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__18);
			setState(118);
			((JrContext)_localctx).dest = match(REG);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32{\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2$\n\2\f"+
		"\2\16\2\'\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\3<\n\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\2"+
		"\2{\2%\3\2\2\2\4;\3\2\2\2\6=\3\2\2\2\b@\3\2\2\2\nB\3\2\2\2\fG\3\2\2\2"+
		"\16L\3\2\2\2\20Q\3\2\2\2\22V\3\2\2\2\24]\3\2\2\2\26a\3\2\2\2\30h\3\2\2"+
		"\2\32l\3\2\2\2\34o\3\2\2\2\36t\3\2\2\2 w\3\2\2\2\"$\5\4\3\2#\"\3\2\2\2"+
		"$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'%\3\2\2\2(<\5\6\4\2)<\5\b"+
		"\5\2*<\5\n\6\2+<\5\f\7\2,<\5\16\b\2-<\5\20\t\2.<\5\22\n\2/<\5\24\13\2"+
		"\60<\5\26\f\2\61<\5\30\r\2\62<\5\32\16\2\63<\5\34\17\2\64<\5\36\20\2\65"+
		"<\5 \21\2\66\67\7\30\2\2\67<\7\3\2\28<\7\4\2\29:\7\5\2\2:<\7\27\2\2;("+
		"\3\2\2\2;)\3\2\2\2;*\3\2\2\2;+\3\2\2\2;,\3\2\2\2;-\3\2\2\2;.\3\2\2\2;"+
		"/\3\2\2\2;\60\3\2\2\2;\61\3\2\2\2;\62\3\2\2\2;\63\3\2\2\2;\64\3\2\2\2"+
		";\65\3\2\2\2;\66\3\2\2\2;8\3\2\2\2;9\3\2\2\2<\5\3\2\2\2=>\7\6\2\2>?\7"+
		"\27\2\2?\7\3\2\2\2@A\7\7\2\2A\t\3\2\2\2BC\7\b\2\2CD\7\27\2\2DE\7\27\2"+
		"\2EF\7\27\2\2F\13\3\2\2\2GH\7\t\2\2HI\7\27\2\2IJ\7\27\2\2JK\7\26\2\2K"+
		"\r\3\2\2\2LM\7\n\2\2MN\7\27\2\2NO\7\27\2\2OP\7\27\2\2P\17\3\2\2\2QR\7"+
		"\13\2\2RS\7\27\2\2ST\7\27\2\2TU\7\26\2\2U\21\3\2\2\2VW\7\f\2\2WX\7\27"+
		"\2\2XY\7\26\2\2YZ\7\r\2\2Z[\7\27\2\2[\\\7\16\2\2\\\23\3\2\2\2]^\7\17\2"+
		"\2^_\7\27\2\2_`\7\26\2\2`\25\3\2\2\2ab\7\20\2\2bc\7\27\2\2cd\7\26\2\2"+
		"de\7\r\2\2ef\7\27\2\2fg\7\16\2\2g\27\3\2\2\2hi\7\21\2\2ij\7\27\2\2jk\7"+
		"\27\2\2k\31\3\2\2\2lm\7\22\2\2mn\7\30\2\2n\33\3\2\2\2op\7\23\2\2pq\7\27"+
		"\2\2qr\7\27\2\2rs\7\30\2\2s\35\3\2\2\2tu\7\24\2\2uv\7\30\2\2v\37\3\2\2"+
		"\2wx\7\25\2\2xy\7\27\2\2y!\3\2\2\2\4%;";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}