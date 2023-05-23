package io.proleap.vb6.transform.java.rules.lang.statement.select;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.CaseCondExprContext;
import io.proleap.vb6.VisualBasic6Parser.SC_CondExprContext;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class CaseCondExpressionRule extends VbTransformRule<CaseCondExprContext, SelectCaseCond> {

	@Override
	public void apply(final CaseCondExprContext ctx, final SelectCaseCond selectCaseCond, final RuleContext rc) {
		for (final SC_CondExprContext sc_CondExprContext : ctx.sC_CondExpr()) {
			rc.visit(sc_CondExprContext);
		}
	}

	@Override
	public Class<CaseCondExprContext> from() {
		return CaseCondExprContext.class;
	}
}
