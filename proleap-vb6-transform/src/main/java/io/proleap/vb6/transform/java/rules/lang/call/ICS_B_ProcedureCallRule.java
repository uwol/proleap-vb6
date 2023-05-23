package io.proleap.vb6.transform.java.rules.lang.call;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_B_ProcedureCallContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ICS_B_ProcedureCallRule extends VbTransformRule<ICS_B_ProcedureCallContext, Call> {

	@Override
	public void apply(final ICS_B_ProcedureCallContext ctx, final Call call, final RuleContext rc) {
		final String identifier = call.getName();

		rc.p("%s(", identifier);

		if (ctx.argsCall() != null) {
			rc.visit(ctx.argsCall());
		}

		rc.p(")");
	}

	@Override
	public Class<ICS_B_ProcedureCallContext> from() {
		return ICS_B_ProcedureCallContext.class;
	}
}
