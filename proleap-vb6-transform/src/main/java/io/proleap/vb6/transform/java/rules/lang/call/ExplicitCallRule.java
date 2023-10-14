package io.proleap.vb6.transform.java.rules.lang.call;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ExplicitCallStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ExplicitCallRule extends VbTransformRule<ExplicitCallStmtContext, Call> {

	@Override
	public void apply(final ExplicitCallStmtContext ctx, final Call call, final RuleContext rc) {
		rc.visitChildren(ctx);

		rc.p(";");
		rc.pNl(call);
	}

	@Override
	public Class<ExplicitCallStmtContext> from() {
		return ExplicitCallStmtContext.class;
	}
}
