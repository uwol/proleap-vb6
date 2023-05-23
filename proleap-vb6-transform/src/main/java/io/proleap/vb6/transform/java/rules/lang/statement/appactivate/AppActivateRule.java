package io.proleap.vb6.transform.java.rules.lang.statement.appactivate;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.AppActivateStmtContext;
import io.proleap.vb6.asg.metamodel.statement.appactivate.AppActivate;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class AppActivateRule extends VbTransformRule<AppActivateStmtContext, AppActivate> {

	@Override
	public void apply(final AppActivateStmtContext ctx, final AppActivate appActivate, final RuleContext rc) {
		rc.p("AppActivate(");

		rc.visit(ctx.valueStmt(0));

		if (ctx.valueStmt().size() > 1) {
			rc.p(", ");
			rc.visit(ctx.valueStmt(1));
		}

		rc.p(");");
		rc.pNl(appActivate);
	}

	@Override
	public Class<AppActivateStmtContext> from() {
		return AppActivateStmtContext.class;
	}
}
