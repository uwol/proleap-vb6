package io.proleap.vb6.transform.java.rules.lang.statement.ifstmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.IfConditionStmtContext;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.IfCondition;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class IfConditionStmtRule extends VbTransformRule<IfConditionStmtContext, IfCondition> {

	@Override
	public void apply(final IfConditionStmtContext ctx, final IfCondition ifCondition, final RuleContext rc) {
		final Type valueStmtType = ifCondition.getValueStmt().getType();

		rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(), valueStmtType, VbBaseType.BOOLEAN);
	}

	@Override
	public Class<IfConditionStmtContext> from() {
		return IfConditionStmtContext.class;
	}
}
