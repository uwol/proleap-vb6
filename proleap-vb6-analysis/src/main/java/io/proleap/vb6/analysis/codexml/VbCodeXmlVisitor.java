package io.proleap.vb6.analysis.codexml;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.vb6.VisualBasic6BaseVisitor;
import io.proleap.vb6.VisualBasic6Parser.AmbiguousIdentifierContext;
import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.CertainIdentifierContext;
import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ECS_MemberProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ECS_ProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.VisualBasic6Parser.FunctionStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_B_MemberProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_B_ProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_ProcedureOrArrayCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_VariableOrProcedureCallContext;
import io.proleap.vb6.VisualBasic6Parser.LiteralContext;
import io.proleap.vb6.VisualBasic6Parser.ModuleContext;
import io.proleap.vb6.VisualBasic6Parser.PropertyGetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertyLetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.PropertySetStmtContext;
import io.proleap.vb6.VisualBasic6Parser.SubStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VariableSubStmtContext;
import io.proleap.vb6.analysis.registry.VbIdRegistry;
import io.proleap.vb6.analysis.util.ANTLRUtils;
import io.proleap.vb6.analysis.util.NamingUtils;
import io.proleap.vb6.analysis.util.TokenUtils;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.ArgCall;
import io.proleap.vb6.asg.metamodel.call.ArrayElementCall;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.ConstantCall;
import io.proleap.vb6.asg.metamodel.call.ElementVariableCall;
import io.proleap.vb6.asg.metamodel.call.EnumerationCall;
import io.proleap.vb6.asg.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.call.FunctionCall;
import io.proleap.vb6.asg.metamodel.call.MeCall;
import io.proleap.vb6.asg.metamodel.call.ModuleCall;
import io.proleap.vb6.asg.metamodel.call.PropertyGetCall;
import io.proleap.vb6.asg.metamodel.call.PropertyLetCall;
import io.proleap.vb6.asg.metamodel.call.PropertySetCall;
import io.proleap.vb6.asg.metamodel.call.ReturnValueCall;
import io.proleap.vb6.asg.metamodel.call.SubCall;
import io.proleap.vb6.asg.metamodel.call.VariableCall;
import io.proleap.vb6.asg.metamodel.registry.ASGElementRegistry;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;

public class VbCodeXmlVisitor extends VisualBasic6BaseVisitor<Boolean> {

	private static final String A = "a";

	private static final String CLASS = "class";

	private static final String COMMA = ",";

	private static final String DATA_CALLERS = "data-callers";

	private static final String DATA_HREFS = "data-hrefs";

	private static final String DIV = "div";

	private static final String HREF = "href";

	private static final String ID = "id";

	private final static Logger LOG = LoggerFactory.getLogger(VbCodeXmlVisitor.class);

	private static final String PRE = "pre";

	private static final String SPAN = "span";

	private static final String X_C = "x-c";

	private static final String X_I = "x-i";

	private static final String X_L = "x-l";

	protected final Document document;

	protected VbDomElementRegistry domElementRegistry = new VbDomElementRegistry();

	protected VbIdRegistry idRegistry;

	protected final Module module;

	public VbCodeXmlVisitor(final Module module, final Document document, final VbIdRegistry idRegistry) {
		this.module = module;
		this.document = document;
		this.idRegistry = idRegistry;
	}

	protected Element addA(final ParserRuleContext ctx) {
		return findParentDomElement(ctx).addElement(A);
	}

	protected void addCall(final AmbiguousIdentifierContext ambiguousIdentifierChildContext,
			final ParserRuleContext ctx) {
		final Element element = addSpan(ctx).addAttribute(ID, idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		ANTLRUtils.visitChildrenTo(ctx, ambiguousIdentifierChildContext, this);
		addCall(ambiguousIdentifierChildContext);
		ANTLRUtils.visitChildrenFrom(ctx, ambiguousIdentifierChildContext, this);
	}

	protected void addCall(final CertainIdentifierContext certainIdentifierChildContext, final ParserRuleContext ctx) {
		final Element element = addSpan(ctx).addAttribute(ID, idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		ANTLRUtils.visitChildrenTo(ctx, certainIdentifierChildContext, this);
		addCall(certainIdentifierChildContext);
		ANTLRUtils.visitChildrenFrom(ctx, certainIdentifierChildContext, this);
	}

	protected Boolean addCall(final ParserRuleContext identifierChildCtx) {
		final Call parentCall = (Call) findParentASGElement(Call.class, identifierChildCtx);
		final Element element;

		if (parentCall != null) {
			final Call unwrappedCall = parentCall.unwrap();
			final String hrefValue = getCallId(unwrappedCall);

			if (hrefValue != null && !hrefValue.isEmpty()) {
				element = addA(identifierChildCtx).addAttribute(ID, idRegistry.assureId(identifierChildCtx))
						.addAttribute(HREF, hrefValue).addElement(X_I);
			} else {
				element = addI(identifierChildCtx);
			}
		} else {
			element = addI(identifierChildCtx);
		}

		domElementRegistry.put(identifierChildCtx, element);

		return visitChildren(identifierChildCtx);
	}

	protected Boolean addDeclaration(final String ids, final String callerNames, final ParserRuleContext identifierCtx,
			final RuleNode ctx) {
		ANTLRUtils.visitChildrenTo(ctx, identifierCtx, this);

		final Element identifierElement = addSpan(identifierCtx).addAttribute(ID, idRegistry.assureId(identifierCtx))
				.addAttribute(DATA_HREFS, ids).addAttribute(DATA_CALLERS, callerNames).addElement(X_I);
		domElementRegistry.put(identifierCtx, identifierElement);
		visitChildren(identifierCtx);

		ANTLRUtils.visitChildrenFrom(ctx, identifierCtx, this);
		return true;
	}

	protected Element addDiv(final ParserRuleContext ctx) {
		return findParentDomElement(ctx).addElement(DIV);
	}

	protected Element addI(final ParserRuleContext ctx) {
		return findParentDomElement(ctx).addElement(X_I);
	}

	protected Element addSpan(final ParserRuleContext ctx) {
		return findParentDomElement(ctx).addElement(SPAN);
	}

	protected <T extends ASGElement> T findParentASGElement(final Class<? extends ASGElement> type,
			final ParserRuleContext from) {
		return io.proleap.vb6.asg.util.ANTLRUtils.findParent(type, from, module.getProgram().getASGElementRegistry());
	}

	protected Element findParentDomElement(final ParseTree ctx) {
		return domElementRegistry.findParentDomElement(ctx);
	}

	protected ASGElement getASGElement(final ParserRuleContext ctx) {
		final Program program = module.getProgram();
		final ASGElementRegistry asgElementRegistry = program.getASGElementRegistry();
		return asgElementRegistry.getASGElement(ctx);
	}

	protected ParserRuleContext getCalledParseTree(final Call call) {
		final CallType callType = call.getCallType();
		final ParserRuleContext result;

		switch (callType) {
		case API_ENUMERATION_CALL:
		case API_ENUMERATION_CONSTANT_CALL:
		case API_PROCEDURE_CALL:
		case API_PROPERTY_CALL:
			result = null;
			break;
		case ARG_CALL:
			final ArgCall argCall = (ArgCall) call;
			result = argCall.getArg().getCtx();
			break;
		case ARRAY_ELEMENT_CALL:
			final ArrayElementCall arrayElementCall = (ArrayElementCall) call;
			result = arrayElementCall.getVariable().getCtx();
			break;
		case CONSTANT_CALL:
			final ConstantCall constantCall = (ConstantCall) call;
			result = constantCall.getConstant().getCtx();
			break;
		case DICTIONARY_CALL:
			result = null;
			break;
		case ELEMENT_VARIABLE_CALL:
			final ElementVariableCall elementVariableCall = (ElementVariableCall) call;
			result = elementVariableCall.getElementVariable().getCtx();
			break;
		case ENUMERATION_CALL:
			final EnumerationCall enumerationCall = (EnumerationCall) call;
			result = enumerationCall.getEnumeration().getCtx();
			break;
		case ENUMERATION_CONSTANT_CALL:
			final EnumerationConstantCall enumerationConstantCall = (EnumerationConstantCall) call;
			result = enumerationConstantCall.getEnumerationConstant().getCtx();
			break;
		case FUNCTION_CALL:
			final FunctionCall functionCall = (FunctionCall) call;
			result = functionCall.getFunction().getCtx();
			break;
		case ME_CALL:
			final MeCall meCall = (MeCall) call;
			result = meCall.getModule().getCtx();
			break;
		case MODULE_CALL:
			final ModuleCall moduleCall = (ModuleCall) call;
			result = moduleCall.getCalledModule().getCtx();
			break;
		case PROPERTY_GET_CALL:
			final PropertyGetCall propertyGetCall = (PropertyGetCall) call;
			result = propertyGetCall.getPropertyGet().getCtx();
			break;
		case PROPERTY_LET_CALL:
			final PropertyLetCall propertyLetCall = (PropertyLetCall) call;
			result = propertyLetCall.getPropertyLet().getCtx();
			break;
		case PROPERTY_SET_CALL:
			final PropertySetCall propertySetCall = (PropertySetCall) call;
			result = propertySetCall.getPropertySet().getCtx();
			break;
		case RETURN_VALUE_CALL:
			final ReturnValueCall returnValueCall = (ReturnValueCall) call;
			result = returnValueCall.getProcedure().getCtx();
			break;
		case SUB_CALL:
			final SubCall subCall = (SubCall) call;
			result = subCall.getSub().getCtx();
			break;
		case TYPE_ELEMENT_CALL:
		case UNDEFINED_CALL:
			result = null;
			break;
		case VARIABLE_CALL:
			final VariableCall variableCall = (VariableCall) call;
			result = variableCall.getVariable().getCtx();
			break;
		default:
			LOG.warn("Could not determine id for call with type {}.", callType);
			result = null;
			break;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	protected String getCallerIds(final List<?> calls) {
		return ((List<Call>) calls).stream().map(call -> idRegistry.assureRelativeId(call.getCtx(), module))
				.collect(Collectors.joining(COMMA));
	}

	@SuppressWarnings("unchecked")
	protected String getCallerNames(final List<?> calls) {
		return ((List<Call>) calls).stream()
				.map(call -> NamingUtils.determineFullQualifiedName(call.getCtx(), module.getProgram()))
				.collect(Collectors.joining(COMMA));
	}

	protected String getCallId(final Call call) {
		final ParserRuleContext calledParseTree = getCalledParseTree(call);
		final String result;

		if (calledParseTree == null) {
			result = null;
		} else {
			result = idRegistry.assureRelativeId(calledParseTree, module);
		}

		return result;
	}

	@Override
	public Boolean visitAmbiguousIdentifier(final AmbiguousIdentifierContext ctx) {
		final Element element = addI(ctx);
		domElementRegistry.put(ctx, element);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitArg(final ArgContext ctx) {
		final Arg arg = (Arg) getASGElement(ctx);
		final String ids = getCallerIds(arg.getArgCalls());
		final String callerNames = getCallerNames(arg.getArgCalls());

		final Element element = addSpan(ctx).addAttribute(CLASS, "arg").addAttribute(ID, idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}

	@Override
	public Boolean visitCertainIdentifier(final CertainIdentifierContext ctx) {
		final Element element = addI(ctx);
		domElementRegistry.put(ctx, element);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitConstSubStmt(final ConstSubStmtContext ctx) {
		final Constant constant = (Constant) getASGElement(ctx);
		final String ids = getCallerIds(constant.getConstantCalls());
		final String callerNames = getCallerNames(constant.getConstantCalls());

		final Element element = addDiv(ctx).addAttribute(CLASS, "constant").addAttribute(ID, idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}

	@Override
	public Boolean visitECS_MemberProcedureCall(final ECS_MemberProcedureCallContext ctx) {
		addCall(ctx.ambiguousIdentifier(), ctx);

		return true;
	}

	@Override
	public Boolean visitECS_ProcedureCall(final ECS_ProcedureCallContext ctx) {
		addCall(ctx.ambiguousIdentifier(), ctx);

		return true;
	}

	@Override
	public Boolean visitEnumerationStmt(final EnumerationStmtContext ctx) {
		final Enumeration enumeration = (Enumeration) getASGElement(ctx);
		final String ids = getCallerIds(enumeration.getEnumerationCalls());
		final String callerNames = getCallerNames(enumeration.getEnumerationCalls());

		final Element element = addDiv(ctx).addAttribute(CLASS, "enumeration").addAttribute(ID,
				idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}

	@Override
	public Boolean visitEnumerationStmt_Constant(final EnumerationStmt_ConstantContext ctx) {
		final EnumerationConstant enumerationConstant = (EnumerationConstant) getASGElement(ctx);
		final String ids = getCallerIds(enumerationConstant.getEnumerationConstantCalls());
		final String callerNames = getCallerNames(enumerationConstant.getEnumerationConstantCalls());

		final Element element = addDiv(ctx).addAttribute(CLASS, "enumeration-constant").addAttribute(ID,
				idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}

	@Override
	public Boolean visitFunctionStmt(final FunctionStmtContext ctx) {
		final Function function = (Function) getASGElement(ctx);
		final String ids = getCallerIds(function.getFunctionCalls());
		final String callerNames = getCallerNames(function.getFunctionCalls());

		final Element element = addDiv(ctx).addAttribute(CLASS, "function").addAttribute(ID, idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}

	@Override
	public Boolean visitICS_B_MemberProcedureCall(final ICS_B_MemberProcedureCallContext ctx) {
		addCall(ctx.ambiguousIdentifier(), ctx);

		return true;
	}

	@Override
	public Boolean visitICS_B_ProcedureCall(final ICS_B_ProcedureCallContext ctx) {
		addCall(ctx.certainIdentifier(), ctx);

		return true;
	}

	@Override
	public Boolean visitICS_S_ProcedureOrArrayCall(final ICS_S_ProcedureOrArrayCallContext ctx) {
		addCall(ctx.ambiguousIdentifier(), ctx);

		return true;
	}

	@Override
	public Boolean visitICS_S_VariableOrProcedureCall(final ICS_S_VariableOrProcedureCallContext ctx) {
		addCall(ctx.ambiguousIdentifier(), ctx);

		return true;
	}

	@Override
	public Boolean visitLiteral(final LiteralContext ctx) {
		final Element element = findParentDomElement(ctx).addElement(X_L);
		domElementRegistry.put(ctx, element);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitModule(final ModuleContext ctx) {
		final Element element = document.addElement(PRE).addAttribute(CLASS, "module").addAttribute(ID,
				idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPropertyGetStmt(final PropertyGetStmtContext ctx) {
		final PropertyGet propertyGet = (PropertyGet) getASGElement(ctx);
		final String ids = getCallerIds(propertyGet.getPropertyGetCalls());
		final String callerNames = getCallerNames(propertyGet.getPropertyGetCalls());

		final Element element = addDiv(ctx).addAttribute(CLASS, "property-get").addAttribute(ID,
				idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}

	@Override
	public Boolean visitPropertyLetStmt(final PropertyLetStmtContext ctx) {
		final PropertyLet propertyLet = (PropertyLet) getASGElement(ctx);
		final String ids = getCallerIds(propertyLet.getPropertyLetCalls());
		final String callerNames = getCallerNames(propertyLet.getPropertyLetCalls());

		final Element element = addDiv(ctx).addAttribute(CLASS, "property-let").addAttribute(ID,
				idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}

	@Override
	public Boolean visitPropertySetStmt(final PropertySetStmtContext ctx) {
		final PropertySet propertySet = (PropertySet) getASGElement(ctx);
		final String ids = getCallerIds(propertySet.getPropertySetCalls());
		final String callerNames = getCallerNames(propertySet.getPropertySetCalls());

		final Element element = addDiv(ctx).addAttribute(CLASS, "property-set").addAttribute(ID,
				idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}

	@Override
	public Boolean visitSubStmt(final SubStmtContext ctx) {
		final Sub sub = (Sub) getASGElement(ctx);
		final String ids = getCallerIds(sub.getSubCalls());
		final String callerNames = getCallerNames(sub.getSubCalls());

		final Element element = addDiv(ctx).addAttribute(CLASS, "sub").addAttribute(ID, idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}

	@Override
	public Boolean visitTerminal(final TerminalNode node) {
		final Element parent = findParentDomElement(node);
		final CommonTokenStream tokens = module.getTokens();
		final int tokPos = node.getSourceInterval().a;
		final String hiddenTokensToLeft = TokenUtils.getHiddenTokensToLeft(tokPos, tokens);

		if (hiddenTokensToLeft == null || hiddenTokensToLeft.isEmpty()) {
			parent.addText(hiddenTokensToLeft + node.getText());
		} else {
			parent.addElement(X_C).addText(hiddenTokensToLeft);
			parent.addText(node.getText());
		}

		return true;
	}

	@Override
	public Boolean visitVariableSubStmt(final VariableSubStmtContext ctx) {
		final Variable variable = (Variable) getASGElement(ctx);
		final String ids = getCallerIds(variable.getVariableCalls());
		final String callerNames = getCallerNames(variable.getVariableCalls());

		final Element element = addDiv(ctx).addAttribute(CLASS, "variable").addAttribute(ID, idRegistry.assureId(ctx));
		domElementRegistry.put(ctx, element);

		return addDeclaration(ids, callerNames, ctx.ambiguousIdentifier(), ctx);
	}
}
