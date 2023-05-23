package io.proleap.vb6.transform.java.rules.lang.statement.constant;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ConstStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ConstRule extends VbTransformRule<ConstStmtContext, ASGElement> {

	@Override
	public void apply(final ConstStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		for (final ConstSubStmtContext constSubExpr : ctx.constSubStmt()) {
			rc.visit(constSubExpr);
		}
	}

	@Override
	public Class<ConstStmtContext> from() {
		return ConstStmtContext.class;
	}
}
