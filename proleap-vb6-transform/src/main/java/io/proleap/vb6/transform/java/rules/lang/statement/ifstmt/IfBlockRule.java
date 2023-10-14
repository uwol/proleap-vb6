package io.proleap.vb6.transform.java.rules.lang.statement.ifstmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.IfBlockStmtContext;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.IfBlock;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class IfBlockRule extends VbTransformRule<IfBlockStmtContext, IfBlock> {

	@Override
	public void apply(final IfBlockStmtContext ctx, final IfBlock ifBlock, final RuleContext rc) {
		rc.p("if(");
		rc.visit(ctx.ifConditionStmt());
		rc.p("){");
		rc.pNl(ifBlock);
		rc.getPrinter().indent();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<IfBlockStmtContext> from() {
		return IfBlockStmtContext.class;
	}
}
