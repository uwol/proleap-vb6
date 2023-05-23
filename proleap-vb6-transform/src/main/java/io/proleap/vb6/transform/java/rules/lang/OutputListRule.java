package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.OutputListContext;
import io.proleap.vb6.VisualBasic6Parser.OutputList_ExpressionContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class OutputListRule extends VbTransformRule<OutputListContext, ASGElement> {

	@Override
	public void apply(final OutputListContext ctx, final ASGElement asgElement, final RuleContext rc) {
		boolean firstEntry = true;

		for (final OutputList_ExpressionContext ole : ctx.outputList_Expression()) {
			if (!firstEntry) {
				rc.p(", ");
			}

			rc.visit(ole);

			firstEntry = false;
		}
	}

	@Override
	public Class<OutputListContext> from() {
		return OutputListContext.class;
	}
}
