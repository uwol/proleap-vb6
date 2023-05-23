package io.proleap.vb6.transform.java.rules.lang.statement.close;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser;
import io.proleap.vb6.VisualBasic6Parser.CloseStmtContext;
import io.proleap.vb6.asg.metamodel.statement.close.Close;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class CloseRule extends VbTransformRule<CloseStmtContext, Close> {

	@Override
	public void apply(final CloseStmtContext ctx, final Close close, final RuleContext rc) {
		rc.p("Close(");

		boolean isFirstEntry = true;
		for (final VisualBasic6Parser.ValueStmtContext vs : ctx.valueStmt()) {
			if (!isFirstEntry) {
				rc.p(",");
			}

			rc.visit(vs);

			isFirstEntry = false;
		}

		rc.visitChildren(ctx);

		rc.p(");");
		rc.pNl(close);
	}

	@Override
	public Class<CloseStmtContext> from() {
		return CloseStmtContext.class;
	}
}
