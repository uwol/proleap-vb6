package io.proleap.vb6.transform.java.rules.lang.call;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_B_MemberProcedureCallContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.transform.java.rules.lang.statement.with.WithRule;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ICS_B_MemberProcedureCallRule extends VbTransformRule<ICS_B_MemberProcedureCallContext, Call> {

	@Override
	public void apply(final ICS_B_MemberProcedureCallContext ctx, final Call call, final RuleContext rc) {
		if (ctx.implicitCallStmt_InStmt() != null) {
			rc.visit(ctx.implicitCallStmt_InStmt());
		} else {
			rc.p(WithRule.WITH_CONTEXT_VAR_NAME);
		}

		rc.p(".");

		final String identifier = call.getName();

		rc.p("%s(", identifier);

		if (ctx.argsCall() != null) {
			rc.visit(ctx.argsCall());
		}

		rc.p(")");
	}

	@Override
	public Class<ICS_B_MemberProcedureCallContext> from() {
		return ICS_B_MemberProcedureCallContext.class;
	}
}
