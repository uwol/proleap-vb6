package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsNotContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.NotValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsNotRule extends VsOperatorRule<VsNotContext, NotValueStmt> {

	@Override
	public void apply(final VsNotContext ctx, final NotValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.NOT, ctx.valueStmt());
	}

	@Override
	public Class<VsNotContext> from() {
		return VsNotContext.class;
	}
}
