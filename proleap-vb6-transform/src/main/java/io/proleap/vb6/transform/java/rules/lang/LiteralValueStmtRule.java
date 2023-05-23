package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsLiteralContext;
import io.proleap.vb6.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class LiteralValueStmtRule extends VbTransformRule<VsLiteralContext, LiteralValueStmt> {

	@Override
	public void apply(final VsLiteralContext ctx, final LiteralValueStmt literalValueStmt, final RuleContext rc) {
		rc.visit(literalValueStmt.getLiteral().getCtx());
	}

	@Override
	public Class<VsLiteralContext> from() {
		return VsLiteralContext.class;
	}
}
