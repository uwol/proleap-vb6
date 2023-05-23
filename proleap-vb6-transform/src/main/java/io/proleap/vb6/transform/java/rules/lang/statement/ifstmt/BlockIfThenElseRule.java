package io.proleap.vb6.transform.java.rules.lang.statement.ifstmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.BlockIfThenElseContext;
import io.proleap.vb6.VisualBasic6Parser.IfElseIfBlockStmtContext;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.BlockIfThenElse;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class BlockIfThenElseRule extends VbTransformRule<BlockIfThenElseContext, BlockIfThenElse> {

	@Override
	public void apply(final BlockIfThenElseContext ctx, final BlockIfThenElse blockIfThenElse, final RuleContext rc) {
		rc.visit(ctx.ifBlockStmt());

		for (final IfElseIfBlockStmtContext eibe : ctx.ifElseIfBlockStmt()) {
			rc.visit(eibe);
		}

		if (ctx.ifElseBlockStmt() != null) {
			rc.visit(ctx.ifElseBlockStmt());
		}

		rc.pNl();
	}

	@Override
	public Class<BlockIfThenElseContext> from() {
		return BlockIfThenElseContext.class;
	}
}
