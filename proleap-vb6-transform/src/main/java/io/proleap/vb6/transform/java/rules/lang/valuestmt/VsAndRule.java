package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsAndContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsAndRule extends VsOperatorRule<VsAndContext, ValueStmt> {

	@Override
	public void apply(final VsAndContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		printOperator(rc, VbOperator.AND, ctx.valueStmt());
	}

	@Override
	public Class<VsAndContext> from() {
		return VsAndContext.class;
	}
}
