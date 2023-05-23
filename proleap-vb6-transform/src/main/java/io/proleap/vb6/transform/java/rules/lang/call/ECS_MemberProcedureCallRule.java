package io.proleap.vb6.transform.java.rules.lang.call;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ECS_MemberProcedureCallContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ECS_MemberProcedureCallRule extends VbTransformRule<ECS_MemberProcedureCallContext, Call> {

	@Override
	public void apply(final ECS_MemberProcedureCallContext ctx, final Call call, final RuleContext rc) {
		final String identifier = call.getName();

		if (ctx.implicitCallStmt_InStmt() != null) {
			rc.visit(ctx.implicitCallStmt_InStmt());
		}

		rc.p(".");
		rc.p("%s(", identifier);

		if (ctx.argsCall() != null) {
			rc.visit(ctx.argsCall());
		}

		rc.p(")");
	}

	@Override
	public Class<ECS_MemberProcedureCallContext> from() {
		return ECS_MemberProcedureCallContext.class;
	}
}
