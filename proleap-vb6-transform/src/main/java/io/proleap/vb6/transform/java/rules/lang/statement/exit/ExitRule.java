package io.proleap.vb6.transform.java.rules.lang.statement.exit;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ExitStmtContext;
import io.proleap.vb6.asg.metamodel.statement.exit.Exit;
import io.proleap.vb6.asg.metamodel.statement.exit.Exit.ExitType;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ExitRule extends VbTransformRule<ExitStmtContext, Exit> {

	@Override
	public void apply(final ExitStmtContext ctx, final Exit exit, final RuleContext rc) {
		final ExitType exitType = exit.getExitType();

		switch (exitType) {
		case Do:
			rc.p("break;");
			break;
		case For:
			rc.p("break;");
			break;
		case Function:
			rc.p("// FXIME: return null;");
			break;
		case Property:
			rc.p("// FXIME: return null;");
			break;
		case Sub:
			rc.p("// FXIME: return;");
			break;
		}

		rc.pNl(exit);
	}

	@Override
	public Class<ExitStmtContext> from() {
		return ExitStmtContext.class;
	}
}
