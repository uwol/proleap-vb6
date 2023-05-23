package io.proleap.vb6.transform.java.rules.lang.statement.ifstmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.IfElseIfBlockStmtContext;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.ElseIfBlock;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class IfElseIfBlockRule extends VbTransformRule<IfElseIfBlockStmtContext, ElseIfBlock> {

	@Override
	public void apply(final IfElseIfBlockStmtContext ctx, final ElseIfBlock elseIfBlock, final RuleContext rc) {
		rc.p("else if(");
		rc.visit(ctx.ifConditionStmt());
		rc.p("){");
		rc.pNl(elseIfBlock);
		rc.getPrinter().indent();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<IfElseIfBlockStmtContext> from() {
		return IfElseIfBlockStmtContext.class;
	}
}
