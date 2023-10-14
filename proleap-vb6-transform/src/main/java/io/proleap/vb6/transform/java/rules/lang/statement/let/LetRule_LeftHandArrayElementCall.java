package io.proleap.vb6.transform.java.rules.lang.statement.let;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_S_ProcedureOrArrayCallContext;
import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InStmtContext;
import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class LetRule_LeftHandArrayElementCall extends VbTransformRule<LetStmtContext, Let> {

	@Override
	public void apply(final LetStmtContext ctx, final Let let, final RuleContext rc) {
		final Call leftHandCall = let.getLeftHandCall();
		final ValueStmt rightHandValueStmt = let.getRightHandValueStmt();
		final Type leftHandType = leftHandCall.getType();
		final Type rightHandType = rightHandValueStmt.getType();

		final ImplicitCallStmt_InStmtContext implicitCall = ctx.implicitCallStmt_InStmt();
		final ICS_S_ProcedureOrArrayCallContext implicitFunctionOrArrayCall = implicitCall.iCS_S_ProcedureOrArrayCall();

		final Call call = (Call) rc.getProgram().getASGElementRegistry().getASGElement(implicitFunctionOrArrayCall);
		final String identifier = call.getName();

		rc.p(identifier);
		rc.p(".setElement(");

		if (!implicitFunctionOrArrayCall.argsCall().isEmpty()) {
			rc.visit(implicitFunctionOrArrayCall.argsCall().get(0));
		}

		rc.p(", ");
		rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(), rightHandType, leftHandType);
		rc.p(")");
		rc.p(";");
		rc.pNl(let);
	}

	@Override
	public Class<LetStmtContext> from() {
		return LetStmtContext.class;
	}

	@Override
	public Integer getPriority() {
		return 256;
	}

	@Override
	public boolean where(final LetStmtContext ctx, final Let let, final RuleContext rc) {
		final ImplicitCallStmt_InStmtContext implicitCall = ctx.implicitCallStmt_InStmt();

		final Call leftHandCall = let.getLeftHandCall();
		final CallType leftHandCallType = leftHandCall.getCallType();

		// if a property let or set has to be called
		final boolean result = CallType.ARRAY_ELEMENT_CALL.equals(leftHandCallType)
				&& implicitCall.iCS_S_ProcedureOrArrayCall() != null;
		return result;
	}
}
