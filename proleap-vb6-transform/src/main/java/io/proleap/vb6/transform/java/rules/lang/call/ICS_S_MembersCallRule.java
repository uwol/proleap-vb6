package io.proleap.vb6.transform.java.rules.lang.call;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_S_MemberCallContext;
import io.proleap.vb6.VisualBasic6Parser.ICS_S_MembersCallContext;
import io.proleap.vb6.asg.metamodel.call.MembersCall;
import io.proleap.vb6.transform.java.rules.lang.statement.with.WithRule;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ICS_S_MembersCallRule extends VbTransformRule<ICS_S_MembersCallContext, MembersCall> {

	@Override
	public void apply(final ICS_S_MembersCallContext ctx, final MembersCall membersCall, final RuleContext rc) {
		if (ctx.iCS_S_VariableOrProcedureCall() != null) {
			rc.visit(ctx.iCS_S_VariableOrProcedureCall());
		} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
			rc.visit(ctx.iCS_S_ProcedureOrArrayCall());
		} else {
			rc.p(WithRule.WITH_CONTEXT_VAR_NAME);
		}

		for (final ICS_S_MemberCallContext iMemberCallContext : ctx.iCS_S_MemberCall()) {
			rc.visit(iMemberCallContext);
		}

		if (ctx.dictionaryCallStmt() != null) {
			rc.visit(ctx.dictionaryCallStmt());
		}
	}

	@Override
	public Class<ICS_S_MembersCallContext> from() {
		return ICS_S_MembersCallContext.class;
	}
}
