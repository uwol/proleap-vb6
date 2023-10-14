package io.proleap.vb6.transform.java.rules.lang.statement.let;

import java.util.List;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_S_MemberCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_MembersCallContext;
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
public class LetRule_LeftHandMemberArrayElementCall extends VbTransformRule<LetStmtContext, Let> {

	@Override
	public void apply(final LetStmtContext ctx, final Let let, final RuleContext rc) {
		final ImplicitCallStmt_InStmtContext implicitCall = ctx.implicitCallStmt_InStmt();
		final ICS_S_MembersCallContext implicitMembersCall = implicitCall.iCS_S_MembersCall();

		if (implicitMembersCall.iCS_S_VariableOrProcedureCall() != null) {
			rc.visit(implicitMembersCall.iCS_S_VariableOrProcedureCall());
		} else if (implicitMembersCall.iCS_S_ProcedureOrArrayCall() != null) {
			rc.visit(implicitMembersCall.iCS_S_ProcedureOrArrayCall());
		}

		final List<ICS_S_MemberCallContext> memberCalls = implicitMembersCall.iCS_S_MemberCall();

		for (int i = 0; i < memberCalls.size() - 1; i++) {
			final ICS_S_MemberCallContext memberCall = memberCalls.get(i);
			rc.visit(memberCall);
		}

		final ICS_S_MemberCallContext lastMemberCall = memberCalls.get(memberCalls.size() - 1);
		printLastMemberCall(ctx, let, lastMemberCall, rc);
	}

	@Override
	public Class<LetStmtContext> from() {
		return LetStmtContext.class;
	}

	@Override
	public Integer getPriority() {
		return 256;
	}

	protected void printLastMemberCall(final LetStmtContext ctx, final Let let,
			final ICS_S_MemberCallContext lastMemberCall, final RuleContext rc) {
		if (lastMemberCall.iCS_S_VariableOrProcedureCall() != null) {
			rc.visit(lastMemberCall.iCS_S_VariableOrProcedureCall());
		} else if (lastMemberCall.iCS_S_ProcedureOrArrayCall() != null) {
			final ICS_S_ProcedureOrArrayCallContext lastMemberFunctionOrArrayCall = lastMemberCall
					.iCS_S_ProcedureOrArrayCall();

			final Call call = (Call) rc.getProgram().getASGElementRegistry()
					.getASGElement(lastMemberFunctionOrArrayCall);
			final String identifier = call.getName();

			rc.p(identifier);
			rc.p(".setElement(");

			if (!lastMemberFunctionOrArrayCall.argsCall().isEmpty()) {
				rc.visit(lastMemberFunctionOrArrayCall.argsCall().get(0));
			}

			rc.p(", ");
			final Call leftHandCall = let.getLeftHandCall();
			final Type leftHandType = leftHandCall.getType();

			final ValueStmt rightHandValueStmt = let.getRightHandValueStmt();
			final Type rightHandType = rightHandValueStmt.getType();

			rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(), rightHandType, leftHandType);
			rc.p(")");
		}

		rc.p(";");
		rc.pNl(let);
	}

	@Override
	public boolean where(final LetStmtContext ctx, final Let let, final RuleContext rc) {
		final ImplicitCallStmt_InStmtContext implicitCall = ctx.implicitCallStmt_InStmt();

		final Call leftHandCall = let.getLeftHandCall();
		final CallType leftHandCallType = leftHandCall.getCallType();

		// if a property let or set has to be called
		final boolean result = CallType.ARRAY_ELEMENT_CALL.equals(leftHandCallType)
				&& implicitCall.iCS_S_MembersCall() != null;
		return result;
	}
}
