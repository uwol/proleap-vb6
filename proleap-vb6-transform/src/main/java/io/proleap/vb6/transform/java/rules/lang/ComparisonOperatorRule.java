package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ComparisonOperatorContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ComparisonOperatorRule extends VbTransformRule<ComparisonOperatorContext, ASGElement> {

	@Override
	public void apply(final ComparisonOperatorContext ctx, final ASGElement asgElement, final RuleContext rc) {
		final String javaOperator;

		if (ctx.EQ() != null) {
			javaOperator = "==";
		} else if (ctx.NEQ() != null) {
			javaOperator = "!=";
		} else if (ctx.GEQ() != null) {
			javaOperator = ">=";
		} else if (ctx.GT() != null) {
			javaOperator = ">";
		} else if (ctx.LEQ() != null) {
			javaOperator = "<=";
		} else if (ctx.LT() != null) {
			javaOperator = "<";
		} else {
			javaOperator = null;
		}

		rc.p(" %s ", javaOperator);
	}

	@Override
	public Class<ComparisonOperatorContext> from() {
		return ComparisonOperatorContext.class;
	}
}
