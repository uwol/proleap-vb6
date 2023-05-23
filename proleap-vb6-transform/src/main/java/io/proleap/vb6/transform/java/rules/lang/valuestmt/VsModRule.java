package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsModContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsModRule extends VsOperatorRule<VsModContext, ValueStmt> {

	@Override
	public void apply(final VsModContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.MOD, ctx.valueStmt());
	}

	@Override
	public Class<VsModContext> from() {
		return VsModContext.class;
	}
}
