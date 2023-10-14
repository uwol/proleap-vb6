package io.proleap.vb6.transform.java.rules.lang.statement.width;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.WidthStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class WidthRule extends VbTransformRule<WidthStmtContext, ASGElement> {

	@Override
	public void apply(final WidthStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		rc.visitChildren(ctx);
	}

	@Override
	public Class<WidthStmtContext> from() {
		return WidthStmtContext.class;
	}
}
