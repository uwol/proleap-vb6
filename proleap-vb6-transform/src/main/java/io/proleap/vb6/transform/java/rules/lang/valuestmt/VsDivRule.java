package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsDivContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsDivRule extends VsOperatorRule<VsDivContext, ValueStmt> {

	@Override
	public void apply(final VsDivContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.DIV, ctx.valueStmt());
	}

	@Override
	public Class<VsDivContext> from() {
		return VsDivContext.class;
	}
}
