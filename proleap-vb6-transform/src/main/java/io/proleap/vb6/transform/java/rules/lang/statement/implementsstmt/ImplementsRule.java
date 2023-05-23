package io.proleap.vb6.transform.java.rules.lang.statement.implementsstmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ImplementsStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ImplementsRule extends VbTransformRule<ImplementsStmtContext, ASGElement> {

	@Override
	public void apply(final ImplementsStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		rc.visitChildren(ctx);
	}

	@Override
	public Class<ImplementsStmtContext> from() {
		return ImplementsStmtContext.class;
	}
}
