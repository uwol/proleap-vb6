package io.proleap.vb6.transform.java.rules.lang.statement.ifstmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.IfElseBlockStmtContext;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.ElseBlock;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class IfElseBlockRule extends VbTransformRule<IfElseBlockStmtContext, ElseBlock> {

	@Override
	public void apply(final IfElseBlockStmtContext ctx, final ElseBlock elseBlock, final RuleContext rc) {
		rc.p("else{");
		rc.pNl(elseBlock);
		rc.getPrinter().indent();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<IfElseBlockStmtContext> from() {
		return IfElseBlockStmtContext.class;
	}
}
