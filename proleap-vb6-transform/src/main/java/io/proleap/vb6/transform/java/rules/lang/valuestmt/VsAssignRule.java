package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsAssignContext;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueAssignment;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsAssignRule extends VsOperatorRule<VsAssignContext, ValueAssignment> {

	@Override
	public void apply(final VsAssignContext ctx, final ValueAssignment valueAssignment, final RuleContext rc) {
		if (valueAssignment.getPropertyLet() != null || valueAssignment.getPropertySet() != null) {
			rc.visit(ctx.implicitCallStmt_InStmt());
			rc.p("(");
			rc.visit(ctx.valueStmt());
			rc.p(")");
		} else {
			rc.visit(ctx.implicitCallStmt_InStmt());
			rc.p("=");
			rc.visit(ctx.valueStmt());
		}
	}

	@Override
	public Class<VsAssignContext> from() {
		return VsAssignContext.class;
	}
}
