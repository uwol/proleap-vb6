package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.SubscriptContext;
import io.proleap.vb6.VisualBasic6Parser.SubscriptsContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class SubscriptsRule extends VbTransformRule<SubscriptsContext, ASGElement> {

	@Override
	public void apply(final SubscriptsContext ctx, final ASGElement asgElement, final RuleContext rc) {
		boolean firstEntry = true;

		for (final SubscriptContext subscript : ctx.subscript()) {
			if (!firstEntry) {
				rc.p(", ");
			}

			rc.visit(subscript);

			firstEntry = false;
		}
	}

	@Override
	public Class<SubscriptsContext> from() {
		return SubscriptsContext.class;
	}
}
