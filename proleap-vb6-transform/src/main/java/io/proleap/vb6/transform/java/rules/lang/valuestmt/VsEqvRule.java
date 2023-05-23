package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsEqvContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsEqvRule extends VsOperatorRule<VsEqvContext, ValueStmt> {

	@Override
	public void apply(final VsEqvContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.EQV, ctx.valueStmt());
	}

	@Override
	public Class<VsEqvContext> from() {
		return VsEqvContext.class;
	}
}
