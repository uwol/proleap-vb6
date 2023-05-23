package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsStructContext;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsStructRule extends VsOperatorRule<VsStructContext, ValueStmt> {

	@Override
	public void apply(final VsStructContext ctx, final ValueStmt valueStmt, final RuleContext rc) {
		rc.p("(");
		rc.visitChildren(ctx);
		rc.p(")");
	}

	@Override
	public Class<VsStructContext> from() {
		return VsStructContext.class;
	}
}
