package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsICSContext;
import io.proleap.vb6.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class VsICSRule extends VbTransformRule<VsICSContext, CallValueStmt> {

	@Override
	public void apply(final VsICSContext ctx, final CallValueStmt valueStmt, final RuleContext rc) {
		rc.visitChildren(ctx);
	}

	@Override
	public Class<VsICSContext> from() {
		return VsICSContext.class;
	}
}
