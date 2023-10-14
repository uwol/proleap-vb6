package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsLeqContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsLeqRule extends VsOperatorRule<VsLeqContext, ValueStmt> {

	@Override
	public void apply(final VsLeqContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.LEQ, ctx.valueStmt());
	}

	@Override
	public Class<VsLeqContext> from() {
		return VsLeqContext.class;
	}
}
