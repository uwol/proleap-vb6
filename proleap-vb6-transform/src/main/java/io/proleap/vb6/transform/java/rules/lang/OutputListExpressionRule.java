package io.proleap.vb6.transform.java.rules.lang;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.OutputList_ExpressionContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class OutputListExpressionRule extends VbTransformRule<OutputList_ExpressionContext, ASGElement> {

	@Override
	public void apply(final OutputList_ExpressionContext ctx, final ASGElement asgElement, final RuleContext rc) {
		if (ctx.valueStmt() != null) {
			rc.visit(ctx.valueStmt());
		} else if (ctx.argsCall() != null) {
			rc.visit(ctx.argsCall());
		}
	}

	@Override
	public Class<OutputList_ExpressionContext> from() {
		return OutputList_ExpressionContext.class;
	}
}
