package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.VisualBasic6Parser.ArgListContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ArgListRule extends VbTransformRule<ArgListContext, ASGElement> {

	@Override
	public void apply(final ArgListContext ctx, final ASGElement asgElement, final RuleContext rc) {
		boolean firstEntry = true;

		for (final ArgContext pd : ctx.arg()) {
			if (!firstEntry) {
				rc.p(", ");
			}

			rc.visit(pd);
			firstEntry = false;
		}
	}

	@Override
	public Class<ArgListContext> from() {
		return ArgListContext.class;
	}
}
