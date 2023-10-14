package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsXorContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsXorRule extends VsOperatorRule<VsXorContext, ValueStmt> {

	@Override
	public void apply(final VsXorContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.XOR, ctx.valueStmt());
	}

	@Override
	public Class<VsXorContext> from() {
		return VsXorContext.class;
	}
}
