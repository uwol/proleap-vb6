package io.proleap.vb6.transform.java.rules.lang.statement.fornext;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ForNextStmtContext;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.VariableCall;
import io.proleap.vb6.asg.metamodel.statement.fornext.ForNext;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ForNextRule extends VbTransformRule<ForNextStmtContext, ForNext> {

	@Override
	public void apply(final ForNextStmtContext ctx, final ForNext forNext, final RuleContext rc) {
		final Call counterCall = forNext.getCounterCall();
		final Call unwrappedCall = counterCall.unwrap();
		final CallType callType = unwrappedCall.getCallType();

		final String iteratorVariableName;
		final Type iteratorVariableType;

		if (!CallType.VARIABLE_CALL.equals(callType)) {
			iteratorVariableName = "undefined";
			iteratorVariableType = null;
		} else {
			final VariableCall variableCall = (VariableCall) unwrappedCall;
			final Variable iteratorVariable = variableCall.getVariable();

			if (iteratorVariable == null) {
				iteratorVariableName = "undefined";
				iteratorVariableType = null;
			} else {
				iteratorVariableName = iteratorVariable.getName();
				iteratorVariableType = iteratorVariable.getType();
			}
		}

		rc.p("for(%s = ", iteratorVariableName);

		if (!ctx.valueStmt().isEmpty() && ctx.valueStmt(0) != null) {
			final ValueStmt from = forNext.getFrom();
			final Type fromType = from != null ? from.getType() : null;

			rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(0), fromType, iteratorVariableType);
		}

		rc.p("; %s <= ", iteratorVariableName);

		if (ctx.valueStmt().size() > 1 && ctx.valueStmt(1) != null) {
			final ValueStmt to = forNext.getTo();
			final Type toType = to != null ? to.getType() : null;

			rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(1), toType, iteratorVariableType);
		}

		rc.p("; %s++", iteratorVariableName);
		rc.p("){");
		rc.pNl(forNext);
		rc.getPrinter().indent();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<ForNextStmtContext> from() {
		return ForNextStmtContext.class;
	}
}
