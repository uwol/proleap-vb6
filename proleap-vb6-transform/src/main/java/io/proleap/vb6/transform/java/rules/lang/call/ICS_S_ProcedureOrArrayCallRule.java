package io.proleap.vb6.transform.java.rules.lang.call;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_S_ProcedureOrArrayCallContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ICS_S_ProcedureOrArrayCallRule extends VbTransformRule<ICS_S_ProcedureOrArrayCallContext, Call> {

	@Override
	public void apply(final ICS_S_ProcedureOrArrayCallContext ctx, final Call call, final RuleContext rc) {
		final String identifier = call.getName();
		rc.p(identifier);

		rc.p("(");

		if (!ctx.argsCall().isEmpty()) {
			rc.visit(ctx.argsCall().get(0));
		}

		rc.p(")");
	}

	@Override
	public Class<ICS_S_ProcedureOrArrayCallContext> from() {
		return ICS_S_ProcedureOrArrayCallContext.class;
	}
}
