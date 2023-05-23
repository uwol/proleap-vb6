package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsMultContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsMultRule extends VsOperatorRule<VsMultContext, ValueStmt> {

	@Override
	public void apply(final VsMultContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.MULT, ctx.valueStmt());
	}

	@Override
	public Class<VsMultContext> from() {
		return VsMultContext.class;
	}
}
