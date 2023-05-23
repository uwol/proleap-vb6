package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.VisualBasic6Parser.ArgsCallContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ArgsCallRule extends VbTransformRule<ArgsCallContext, ASGElement> {

	@Override
	public void apply(final ArgsCallContext ctx, final ASGElement asgElement, final RuleContext rc) {
		if (ctx != null) {
			boolean firstEntry = true;

			for (final ArgCallContext argCall : ctx.argCall()) {
				if (!firstEntry) {
					rc.p(", ");
				}

				rc.visit(argCall);
				firstEntry = false;
			}
		}
	}

	@Override
	public Class<ArgsCallContext> from() {
		return ArgsCallContext.class;
	}
}
