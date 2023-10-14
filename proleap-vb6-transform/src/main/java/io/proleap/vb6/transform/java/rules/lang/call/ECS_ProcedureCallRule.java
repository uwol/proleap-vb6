package io.proleap.vb6.transform.java.rules.lang.call;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ECS_ProcedureCallContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ECS_ProcedureCallRule extends VbTransformRule<ECS_ProcedureCallContext, Call> {

	@Override
	public void apply(final ECS_ProcedureCallContext ctx, final Call call, final RuleContext rc) {
		final String identifier = call.getName();

		rc.p("%s(", identifier);

		if (ctx.argsCall() != null) {
			rc.visit(ctx.argsCall());
		}

		rc.p(")");
	}

	@Override
	public Class<ECS_ProcedureCallContext> from() {
		return ECS_ProcedureCallContext.class;
	}
}
