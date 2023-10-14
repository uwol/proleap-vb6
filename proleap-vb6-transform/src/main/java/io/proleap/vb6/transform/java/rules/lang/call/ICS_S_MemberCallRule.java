package io.proleap.vb6.transform.java.rules.lang.call;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_S_MemberCallContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ICS_S_MemberCallRule extends VbTransformRule<ICS_S_MemberCallContext, Call> {

	@Override
	public void apply(final ICS_S_MemberCallContext ctx, final Call call, final RuleContext rc) {
		rc.p(".");

		if (ctx.iCS_S_VariableOrProcedureCall() != null) {
			rc.visit(ctx.iCS_S_VariableOrProcedureCall());
		} else if (ctx.iCS_S_ProcedureOrArrayCall() != null) {
			rc.visit(ctx.iCS_S_ProcedureOrArrayCall());
		}
	}

	@Override
	public Class<ICS_S_MemberCallContext> from() {
		return ICS_S_MemberCallContext.class;
	}
}
