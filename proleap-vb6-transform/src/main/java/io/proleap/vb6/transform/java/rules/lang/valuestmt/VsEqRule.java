package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsEqContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.EqValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsEqRule extends VsOperatorRule<VsEqContext, EqValueStmt> {

	@Override
	public void apply(final VsEqContext ctx, final EqValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.EQ, ctx.valueStmt());
	}

	@Override
	public Class<VsEqContext> from() {
		return VsEqContext.class;
	}
}
