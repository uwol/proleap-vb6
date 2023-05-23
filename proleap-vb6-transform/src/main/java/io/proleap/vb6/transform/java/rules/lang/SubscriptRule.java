package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.SubscriptContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class SubscriptRule extends VbTransformRule<SubscriptContext, ASGElement> {

	@Override
	public void apply(final SubscriptContext ctx, final ASGElement asgElement, final RuleContext rc) {
		if (ctx.valueStmt().size() == 1) {
			rc.visit(ctx.valueStmt(0));
		} else if (ctx.valueStmt().size() == 2) {
			rc.visit(ctx.valueStmt(1));
		}
	}

	@Override
	public Class<SubscriptContext> from() {
		return SubscriptContext.class;
	}
}
