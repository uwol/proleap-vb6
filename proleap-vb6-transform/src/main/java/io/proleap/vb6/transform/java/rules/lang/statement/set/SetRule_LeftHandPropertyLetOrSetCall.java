package io.proleap.vb6.transform.java.rules.lang.statement.set;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.SetStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.statement.set.Set;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class SetRule_LeftHandPropertyLetOrSetCall extends VbTransformRule<SetStmtContext, Set> {

	@Override
	public void apply(final SetStmtContext ctx, final Set set, final RuleContext rc) {
		final Call leftHandCall = set.getLeftHandCall();
		final ValueStmt rightHandValueStmt = set.getRightHandValueStmt();
		final Type leftHandType = leftHandCall.getType();
		final Type rightHandType = rightHandValueStmt.getType();

		rc.visit(ctx.implicitCallStmt_InStmt());

		rc.p("(");
		rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(), rightHandType, leftHandType);
		rc.p(");");
		rc.pNl(set);
	}

	@Override
	public Class<SetStmtContext> from() {
		return SetStmtContext.class;
	}

	@Override
	public Integer getPriority() {
		return -1;
	}

	@Override
	public boolean where(final SetStmtContext ctx, final Set set, final RuleContext rc) {
		final Call leftHandCall = set.getLeftHandCall();
		final CallType leftHandCallType = leftHandCall.getCallType();

		// if a property let or set has to be called
		final boolean result = CallType.PROPERTY_LET_CALL.equals(leftHandCallType)
				|| CallType.PROPERTY_SET_CALL.equals(leftHandCallType);
		return result;
	}
}
