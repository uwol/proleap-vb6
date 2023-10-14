package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.TypedElement;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.java.operator.JavaOperator;
import io.proleap.vb6.transform.java.util.OperatorUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public abstract class VsOperatorRule<S extends ValueStmtContext, T extends ValueStmt> extends VbTransformRule<S, T> {

	protected List<Type> getOperandTypes(final RuleContext rc, final ValueStmtContext... operands) {
		/*
		 * determine operand types
		 */
		final List<Type> operandTypes = new ArrayList<Type>();

		for (final ValueStmtContext operand : operands) {
			final TypedElement typedElement = (TypedElement) rc.getProgram().getASGElementRegistry()
					.getASGElement(operand);
			final Type operandType = typedElement.getType();

			operandTypes.add(operandType);
		}

		return operandTypes;
	}

	protected void printOperator(final RuleContext rc, final JavaOperator javaOperator,
			final ValueStmtContext... operands) {
		final List<Type> operandTypes = getOperandTypes(rc, operands);

		/*
		 * determine adjusted operand types
		 */
		final List<Type> adjustedOperandTypes = new ArrayList<Type>();

		if (operandTypes.size() == 2) {
			final Type operand1Type = operandTypes.get(0);
			final Type operand2Type = operandTypes.get(1);

			final Type adjustedOperand1Type = OperatorUtils.adjustOperandType(javaOperator, operand1Type, operand2Type);
			final Type adjustedOperand2Type = OperatorUtils.adjustOperandType(javaOperator, operand2Type, operand1Type);

			adjustedOperandTypes.add(adjustedOperand1Type);
			adjustedOperandTypes.add(adjustedOperand2Type);
		} else {
			adjustedOperandTypes.addAll(operandTypes);
		}

		/*
		 * print operator
		 */
		if (javaOperator.isInfix()) {
			if (operandTypes.size() == 1) {
				rc.p(" %s", javaOperator.getOperator());
				rc.visit(operands[0]);
			} else if (operandTypes.size() == 2) {
				final Type operand1Type = operandTypes.get(0);
				final Type operand2Type = operandTypes.get(1);

				final Type adjustedOperand1Type = adjustedOperandTypes.get(0);
				final Type adjustedOperand2Type = adjustedOperandTypes.get(1);

				/*
				 * print operator + operands
				 */
				rc.getTypedPrinter().printWithAdjustedType(operands[0], operand1Type, adjustedOperand1Type);
				rc.p(" %s ", javaOperator.getOperator());
				rc.getTypedPrinter().printWithAdjustedType(operands[1], operand2Type, adjustedOperand2Type);
			}
		} else {
			rc.p(javaOperator.getOperator());

			rc.p("(");

			for (int i = 0; i < operandTypes.size(); i++) {
				if (i > 0) {
					rc.p(", ");
				}

				final ValueStmtContext operand = operands[i];
				final Type operandType = operandTypes.get(i);
				final Type adjustedOperandType = adjustedOperandTypes.get(i);

				rc.getTypedPrinter().printWithAdjustedType(operand, operandType, adjustedOperandType);
			}

			rc.p(")");
		}

	}

	protected void printOperator(final RuleContext rc, final VbOperator vbOperator,
			final List<ValueStmtContext> operands) {
		final ValueStmtContext[] operandsArray = operands.toArray(new ValueStmtContext[0]);
		printOperator(rc, vbOperator, operandsArray);
	}

	protected void printOperator(final RuleContext rc, final VbOperator vbOperator,
			final ValueStmtContext... operands) {
		final List<Type> operandTypes = getOperandTypes(rc, operands);
		final JavaOperator javaOperator = OperatorUtils.getVb2JavaOperator(vbOperator, operandTypes);

		printOperator(rc, javaOperator, operands);
	}
}
