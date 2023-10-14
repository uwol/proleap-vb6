package io.proleap.vb6.transform.java.rules.lang.statement.select;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.CaseCondExprToContext;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class CaseCondExpressionToRule extends AbstractCaseCondExpressionRule<CaseCondExprToContext> {

	@Override
	public void apply(final CaseCondExprToContext ctx, final SelectCaseCondExpression selectCaseCondExpression,
			final RuleContext rc) {
		for (final ValueStmt valueStmt : selectCaseCondExpression.getValueStmts()) {
			rc.p("case ");

			printValueStmt(valueStmt, selectCaseCondExpression, rc);

			rc.p(":");
			rc.pNl(selectCaseCondExpression);
		}
	}

	@Override
	public Class<CaseCondExprToContext> from() {
		return CaseCondExprToContext.class;
	}
}
