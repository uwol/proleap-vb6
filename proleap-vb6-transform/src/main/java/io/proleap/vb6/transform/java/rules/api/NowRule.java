package io.proleap.vb6.transform.java.rules.api;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_S_VariableOrProcedureCallContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class NowRule extends VbTransformRule<ICS_S_VariableOrProcedureCallContext, Call> {

	@Override
	public void apply(final ICS_S_VariableOrProcedureCallContext ctx, final Call call, final RuleContext rc) {
		rc.p("new Date()");
	}

	@Override
	public Class<ICS_S_VariableOrProcedureCallContext> from() {
		return ICS_S_VariableOrProcedureCallContext.class;
	}

	@Override
	public Integer getPriority() {
		return 256;
	}

	@Override
	public boolean where(final ICS_S_VariableOrProcedureCallContext ctx, final Call call, final RuleContext rc) {
		final Call pattern = new UndefinedCallImpl("Now", null, call.getModule(), null, null);

		final boolean result = pattern.equals(call);
		return result;
	}
}
