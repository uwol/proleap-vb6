package io.proleap.vb6.transform.java.rules.lang.statement.select;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.CaseCondExprValueContext;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class CaseCondExpressionValueRule extends AbstractCaseCondExpressionRule<CaseCondExprValueContext> {

	@Override
	public void apply(final CaseCondExprValueContext ctx, final SelectCaseCondExpression selectCaseCondExpression,
			final RuleContext rc) {
		for (final ValueStmt valueStmt : selectCaseCondExpression.getValueStmts()) {
			rc.p("case ");

			printValueStmt(valueStmt, selectCaseCondExpression, rc);

			rc.p(":");
			rc.pNl(selectCaseCondExpression);
		}
	}

	@Override
	public Class<CaseCondExprValueContext> from() {
		return CaseCondExprValueContext.class;
	}
}
