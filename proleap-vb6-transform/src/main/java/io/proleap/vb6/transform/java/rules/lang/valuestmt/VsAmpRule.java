package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsAmpContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsAmpRule extends VsOperatorRule<VsAmpContext, ValueStmt> {

	@Override
	public void apply(final VsAmpContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.AMP, ctx.valueStmt());
	}

	@Override
	public Class<VsAmpContext> from() {
		return VsAmpContext.class;
	}
}
