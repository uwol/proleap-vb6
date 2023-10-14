package io.proleap.vb6.transform.java.rules.lang.statement.variable;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VariableStmtContext;
import io.proleap.vb6.VisualBasic6Parser.VariableSubStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class VariableRule extends VbTransformRule<VariableStmtContext, ASGElement> {

	@Override
	public void apply(final VariableStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		for (final VariableSubStmtContext dimSub : ctx.variableListStmt().variableSubStmt()) {
			rc.visit(dimSub);
		}
	}

	@Override
	public Class<VariableStmtContext> from() {
		return VariableStmtContext.class;
	}
}
