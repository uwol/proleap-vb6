package io.proleap.vb6.transform.java.rules.lang.statement.write;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.WriteStmtContext;
import io.proleap.vb6.asg.metamodel.statement.write.Write;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class WriteRule extends VbTransformRule<WriteStmtContext, Write> {

	@Override
	public void apply(final WriteStmtContext ctx, final Write write, final RuleContext rc) {
		rc.p("Write(");

		rc.visit(ctx.valueStmt());

		rc.p(", ");
		if (ctx.outputList() != null) {
			rc.visit(ctx.outputList());
		}

		rc.p(");");
		rc.pNl(write);
	}

	@Override
	public Class<WriteStmtContext> from() {
		return WriteStmtContext.class;
	}
}
