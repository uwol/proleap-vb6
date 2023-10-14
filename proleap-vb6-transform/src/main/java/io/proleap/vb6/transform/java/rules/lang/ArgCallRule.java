package io.proleap.vb6.transform.java.rules.lang;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ArgCallContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ArgValueAssignment;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ArgCallRule extends VbTransformRule<ArgCallContext, ArgValueAssignment> {

	@Override
	public void apply(final ArgCallContext ctx, final ArgValueAssignment argValueAssignment, final RuleContext rc) {
		// value assigned to the arg
		final ValueStmt assignedValue = argValueAssignment.getAssignedValueStmt();
		final Type assignedType = assignedValue.getType();

		// arg
		final Arg arg = argValueAssignment.getArg();
		final Type argType = arg != null ? arg.getType() : null;

		// arg calls can be empty
		rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(), assignedType, argType);
	}

	@Override
	public Class<ArgCallContext> from() {
		return ArgCallContext.class;
	}
}
