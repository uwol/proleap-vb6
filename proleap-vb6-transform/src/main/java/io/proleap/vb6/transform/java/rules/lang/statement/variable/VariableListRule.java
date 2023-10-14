package io.proleap.vb6.transform.java.rules.lang.statement.variable;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VariableListStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class VariableListRule extends VbTransformRule<VariableListStmtContext, ASGElement> {

	@Override
	public void apply(final VariableListStmtContext ctx, final ASGElement asgElement, final RuleContext rc) {
		rc.visitChildren(ctx);
	}

	@Override
	public Class<VariableListStmtContext> from() {
		return VariableListStmtContext.class;
	}
}
