package io.proleap.vb6.transform.java.rules.lang.statement.redim;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.RedimSubStmtContext;
import io.proleap.vb6.asg.metamodel.statement.redim.ReDim;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ReDimSubRule extends VbTransformRule<RedimSubStmtContext, ReDim> {

	@Override
	public void apply(final RedimSubStmtContext ctx, final ReDim reDim, final RuleContext rc) {
		final String identifier = reDim.getName();

		rc.p(" %s.reDim(", identifier);

		rc.visit(ctx.subscripts());

		rc.p(");");
		rc.pNl(reDim);
	}

	@Override
	public Class<RedimSubStmtContext> from() {
		return RedimSubStmtContext.class;
	}
}
