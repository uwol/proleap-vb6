package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsLtContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsLtRule extends VsOperatorRule<VsLtContext, ValueStmt> {

	@Override
	public void apply(final VsLtContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.LT, ctx.valueStmt());
	}

	@Override
	public Class<VsLtContext> from() {
		return VsLtContext.class;
	}
}
