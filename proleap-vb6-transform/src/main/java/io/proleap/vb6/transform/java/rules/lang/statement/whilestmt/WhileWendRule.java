package io.proleap.vb6.transform.java.rules.lang.statement.whilestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.BlockContext;
import io.proleap.vb6.VisualBasic6Parser.WhileWendStmtContext;
import io.proleap.vb6.asg.metamodel.statement.whilestmt.While;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class WhileWendRule extends VbTransformRule<WhileWendStmtContext, While> {

	@Override
	public void apply(final WhileWendStmtContext ctx, final While whileElement, final RuleContext rc) {
		final ValueStmt condition = whileElement.getCondition();

		final Type conditionType = condition.getType();

		rc.p("while(");

		if (ctx.valueStmt() != null) {
			rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(), conditionType, VbBaseType.BOOLEAN);
		}

		rc.p("){");
		rc.pNl(whileElement);
		rc.getPrinter().indent();

		for (final BlockContext block : ctx.block()) {
			rc.visit(block);
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<WhileWendStmtContext> from() {
		return WhileWendStmtContext.class;
	}
}
