package io.proleap.vb6.transform.java.rules.lang.statement.open;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.OpenStmtContext;
import io.proleap.vb6.asg.metamodel.statement.open.Open;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class OpenRule extends VbTransformRule<OpenStmtContext, Open> {

	@Override
	public void apply(final OpenStmtContext ctx, final Open open, final RuleContext rc) {
		rc.p("Open(");
		rc.visit(ctx.valueStmt(0));
		rc.p(", ");
		rc.visit(ctx.valueStmt(1));

		if (ctx.valueStmt().size() > 2) {
			rc.p(", ");
			rc.visit(ctx.valueStmt(2));
		}

		rc.p(");");
		rc.pNl(open);
	}

	@Override
	public Class<OpenStmtContext> from() {
		return OpenStmtContext.class;
	}
}
