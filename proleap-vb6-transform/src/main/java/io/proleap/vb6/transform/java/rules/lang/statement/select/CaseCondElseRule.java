package io.proleap.vb6.transform.java.rules.lang.statement.select;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.CaseCondElseContext;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class CaseCondElseRule extends VbTransformRule<CaseCondElseContext, SelectCaseCond> {

	@Override
	public void apply(final CaseCondElseContext ctx, final SelectCaseCond selectCaseCond, final RuleContext rc) {
		rc.p("default:");
		rc.pNl(selectCaseCond);
	}

	@Override
	public Class<CaseCondElseContext> from() {
		return CaseCondElseContext.class;
	}
}
