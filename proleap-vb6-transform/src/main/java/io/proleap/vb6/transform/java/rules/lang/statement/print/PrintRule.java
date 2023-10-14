package io.proleap.vb6.transform.java.rules.lang.statement.print;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.PrintStmtContext;
import io.proleap.vb6.asg.metamodel.statement.print.Print;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class PrintRule extends VbTransformRule<PrintStmtContext, Print> {

	@Override
	public void apply(final PrintStmtContext ctx, final Print print, final RuleContext rc) {
		rc.p("print(");
		rc.visit(ctx.valueStmt());
		rc.p(", ");

		if (ctx.outputList() != null) {
			rc.visit(ctx.outputList());
		}

		rc.p(");");
		rc.pNl(print);
	}

	@Override
	public Class<PrintStmtContext> from() {
		return PrintStmtContext.class;
	}
}
