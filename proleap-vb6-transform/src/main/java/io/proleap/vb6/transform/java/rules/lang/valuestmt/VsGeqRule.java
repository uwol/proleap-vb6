package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsGeqContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsGeqRule extends VsOperatorRule<VsGeqContext, ValueStmt> {

	@Override
	public void apply(final VsGeqContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.GEQ, ctx.valueStmt());
	}

	@Override
	public Class<VsGeqContext> from() {
		return VsGeqContext.class;
	}
}
