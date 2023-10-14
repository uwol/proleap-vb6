package io.proleap.vb6.transform.java.rules.lang.call;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InBlockContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ImplicitCallStmt_InBlockRule extends VbTransformRule<ImplicitCallStmt_InBlockContext, Call> {

	@Override
	public void apply(final ImplicitCallStmt_InBlockContext ctx, final Call call, final RuleContext rc) {
		rc.visitChildren(ctx);

		rc.p(";");
		rc.pNl(call);
	}

	@Override
	public Class<ImplicitCallStmt_InBlockContext> from() {
		return ImplicitCallStmt_InBlockContext.class;
	}
}
