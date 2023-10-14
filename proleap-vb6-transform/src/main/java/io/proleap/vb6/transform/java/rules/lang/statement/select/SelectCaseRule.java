package io.proleap.vb6.transform.java.rules.lang.statement.select;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.SC_CaseContext;
import io.proleap.vb6.VisualBasic6Parser.SelectCaseStmtContext;
import io.proleap.vb6.asg.metamodel.statement.select.Select;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class SelectCaseRule extends VbTransformRule<SelectCaseStmtContext, Select> {

	@Override
	public void apply(final SelectCaseStmtContext ctx, final Select select, final RuleContext rc) {
		rc.p("switch(");

		if (ctx.valueStmt() != null) {
			rc.visit(ctx.valueStmt());
		}

		rc.p("){");
		rc.pNl(select);
		rc.getPrinter().indent();

		for (final SC_CaseContext ce : ctx.sC_Case()) {
			rc.visit(ce);
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<SelectCaseStmtContext> from() {
		return SelectCaseStmtContext.class;
	}
}
