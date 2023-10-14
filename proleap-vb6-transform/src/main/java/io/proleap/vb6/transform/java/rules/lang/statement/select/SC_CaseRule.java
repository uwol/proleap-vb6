package io.proleap.vb6.transform.java.rules.lang.statement.select;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.SC_CaseContext;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCase;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class SC_CaseRule extends VbTransformRule<SC_CaseContext, SelectCase> {

	@Override
	public void apply(final SC_CaseContext ctx, final SelectCase selectCase, final RuleContext rc) {
		rc.visit(ctx.sC_Cond());

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.p("break;");
		rc.pNl(selectCase);
	}

	@Override
	public Class<SC_CaseContext> from() {
		return SC_CaseContext.class;
	}
}
