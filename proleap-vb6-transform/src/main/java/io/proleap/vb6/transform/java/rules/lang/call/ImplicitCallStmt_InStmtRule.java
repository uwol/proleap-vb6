package io.proleap.vb6.transform.java.rules.lang.call;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ImplicitCallStmt_InStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ImplicitCallStmt_InStmtRule extends VbTransformRule<ImplicitCallStmt_InStmtContext, ASGElement> {

	@Override
	public void apply(final ImplicitCallStmt_InStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		rc.visitChildren(ctx);
	}

	@Override
	public Class<ImplicitCallStmt_InStmtContext> from() {
		return ImplicitCallStmt_InStmtContext.class;
	}
}
