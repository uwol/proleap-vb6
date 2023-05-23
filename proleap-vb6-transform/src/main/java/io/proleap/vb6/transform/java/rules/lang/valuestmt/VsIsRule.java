package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsIsContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsIsRule extends VsOperatorRule<VsIsContext, ValueStmt> {

	@Override
	public void apply(final VsIsContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.IS, ctx.valueStmt());
	}

	@Override
	public Class<VsIsContext> from() {
		return VsIsContext.class;
	}
}
