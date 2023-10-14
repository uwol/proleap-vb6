package io.proleap.vb6.transform.java.rules.lang.statement.macro;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.MacroElseBlockStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class MacroElseBlockRule extends VbTransformRule<MacroElseBlockStmtContext, ASGElement> {

	@Override
	public void apply(final MacroElseBlockStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		rc.p("else{");
		rc.pNl();
		rc.getPrinter().indent();

		if (ctx.moduleBody() != null) {
			rc.visit(ctx.moduleBody());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<MacroElseBlockStmtContext> from() {
		return MacroElseBlockStmtContext.class;
	}
}
