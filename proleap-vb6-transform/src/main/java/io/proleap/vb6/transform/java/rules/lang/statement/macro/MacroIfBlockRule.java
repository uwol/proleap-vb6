package io.proleap.vb6.transform.java.rules.lang.statement.macro;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.MacroIfBlockStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class MacroIfBlockRule extends VbTransformRule<MacroIfBlockStmtContext, ASGElement> {

	@Override
	public void apply(final MacroIfBlockStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		rc.pNl();
		rc.p("if(");
		rc.visit(ctx.ifConditionStmt());
		rc.p("){");
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
	public Class<MacroIfBlockStmtContext> from() {
		return MacroIfBlockStmtContext.class;
	}
}
