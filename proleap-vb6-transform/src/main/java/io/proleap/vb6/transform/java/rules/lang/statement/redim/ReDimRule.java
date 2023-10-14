package io.proleap.vb6.transform.java.rules.lang.statement.redim;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.RedimStmtContext;
import io.proleap.vb6.VisualBasic6Parser.RedimSubStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ReDimRule extends VbTransformRule<RedimStmtContext, ASGElement> {

	@Override
	public void apply(final RedimStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		for (final RedimSubStmtContext redimSub : ctx.redimSubStmt()) {
			rc.visit(redimSub);
		}
	}

	@Override
	public Class<RedimStmtContext> from() {
		return RedimStmtContext.class;
	}
}
