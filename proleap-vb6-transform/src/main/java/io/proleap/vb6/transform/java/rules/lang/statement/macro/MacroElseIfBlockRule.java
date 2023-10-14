package io.proleap.vb6.transform.java.rules.lang.statement.macro;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.MacroElseIfBlockStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class MacroElseIfBlockRule extends VbTransformRule<MacroElseIfBlockStmtContext, ASGElement> {

	@Override
	public void apply(final MacroElseIfBlockStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		rc.p("else if(");
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
	public Class<MacroElseIfBlockStmtContext> from() {
		return MacroElseIfBlockStmtContext.class;
	}
}
