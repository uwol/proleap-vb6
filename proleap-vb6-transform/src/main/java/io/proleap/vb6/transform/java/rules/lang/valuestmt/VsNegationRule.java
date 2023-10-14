package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsNegationContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsNegationRule extends VsOperatorRule<VsNegationContext, ValueStmt> {

	@Override
	public void apply(final VsNegationContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.MINUS, ctx.valueStmt());
	}

	@Override
	public Class<VsNegationContext> from() {
		return VsNegationContext.class;
	}
}
