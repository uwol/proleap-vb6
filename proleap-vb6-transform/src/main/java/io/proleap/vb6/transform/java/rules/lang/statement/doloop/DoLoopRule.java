package io.proleap.vb6.transform.java.rules.lang.statement.doloop;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.DoLoopStmtContext;
import io.proleap.vb6.asg.metamodel.statement.doloop.DoLoop;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class DoLoopRule extends VbTransformRule<DoLoopStmtContext, DoLoop> {

	@Override
	public void apply(final DoLoopStmtContext ctx, final DoLoop doLoop, final RuleContext rc) {
		rc.pNl();
		rc.p("while(");

		if (ctx.valueStmt() != null) {
			rc.visit(ctx.valueStmt());
		} else {
			rc.p("true");
		}

		rc.p("){");
		rc.pNl(doLoop);
		rc.getPrinter().indent();

		rc.visit(ctx.block());

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<DoLoopStmtContext> from() {
		return DoLoopStmtContext.class;
	}
}
