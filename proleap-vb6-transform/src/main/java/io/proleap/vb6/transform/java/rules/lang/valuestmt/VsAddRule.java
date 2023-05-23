package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsAddContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsAddRule extends VsOperatorRule<VsAddContext, ValueStmt> {

	@Override
	public void apply(final VsAddContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.PLUS, ctx.valueStmt());
	}

	@Override
	public Class<VsAddContext> from() {
		return VsAddContext.class;
	}
}
