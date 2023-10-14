package io.proleap.vb6.transform.java.rules.lang.statement.select;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.CaseCondExprIsContext;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class CaseCondExpressionIsRule extends AbstractCaseCondExpressionRule<CaseCondExprIsContext> {

	@Override
	public void apply(final CaseCondExprIsContext ctx, final SelectCaseCondExpression selectCaseCondExpression,
			final RuleContext rc) {
		for (final ValueStmt valueStmt : selectCaseCondExpression.getValueStmts()) {
			rc.p("case ");

			printValueStmt(valueStmt, selectCaseCondExpression, rc);

			rc.p(":");
			rc.pNl(selectCaseCondExpression);
		}
	}

	@Override
	public Class<CaseCondExprIsContext> from() {
		return CaseCondExprIsContext.class;
	}
}
