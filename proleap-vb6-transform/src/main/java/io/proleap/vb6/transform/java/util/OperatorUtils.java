package io.proleap.vb6.transform.java.util;

import java.util.List;

import io.proleap.vb6.asg.metamodel.Operator.VbOperator;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.type.BaseType;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.transform.java.operator.JavaOperator;
import io.proleap.vb6.transform.java.operator.OperatorMapping;

public class OperatorUtils {

	public static Type adjustOperandType(final JavaOperator operator, final Type typeToAdjust, final Type otherType) {
		final Type result;

		final Type dateType = VbBaseType.DATE;
		final Type stringType = VbBaseType.STRING;

		if (JavaOperator.PLUS.equals(operator) && (typeToAdjust == dateType || otherType == dateType
				|| typeToAdjust == stringType || otherType == stringType)) {
			result = stringType;
		} else if (operator.requiresSimilarPrimitiveTypes()) {
			result = adjustType(typeToAdjust, otherType);
		} else {
			result = typeToAdjust;
		}

		return result;
	}

	private static Type adjustType(final Type typeToAdjust, final Type otherType) {
		final Type result;

		final Type doubleType = VbBaseType.DOUBLE;
		final Type integerType = VbBaseType.INTEGER;
		final Type longType = VbBaseType.LONG;
		final Type stringType = VbBaseType.STRING;
		final Type dateType = VbBaseType.DATE;
		final Type variantType = VbBaseType.VARIANT;

		if (typeToAdjust == null) {
			result = otherType;
		} else if (typeToAdjust == otherType) {
			result = typeToAdjust;
		} else if (typeToAdjust == variantType) {
			result = otherType;
		} else if (typeToAdjust instanceof Enumeration) {
			if (otherType == integerType) {
				result = integerType;
			} else {
				result = typeToAdjust;
			}
		} else if (typeToAdjust == integerType) {
			if (otherType == dateType) {
				result = dateType;
			} else if (otherType == longType) {
				result = longType;
			} else if (otherType == doubleType) {
				result = doubleType;
			} else if (otherType == stringType) {
				result = stringType;
			} else {
				result = typeToAdjust;
			}
		} else if (typeToAdjust == longType) {
			if (otherType == dateType) {
				result = dateType;
			} else if (otherType == doubleType) {
				result = doubleType;
			} else if (otherType == stringType) {
				result = stringType;
			} else {
				result = typeToAdjust;
			}
		} else if (typeToAdjust == doubleType) {
			if (otherType == dateType) {
				result = dateType;
			} else if (otherType == stringType) {
				result = stringType;
			} else {
				result = typeToAdjust;
			}
		} else if (typeToAdjust == stringType) {
			if (otherType == dateType) {
				result = dateType;
			} else {
				result = typeToAdjust;
			}
		} else {
			result = typeToAdjust;
		}

		return result;
	}

	public static JavaOperator getVb2JavaOperator(final VbOperator vbOperator, final List<Type> operandTypes) {
		final JavaOperator result;

		final BaseType dateType = VbBaseType.DATE;

		if (VbOperator.EQ.equals(vbOperator)) {
			final Type operand1Type = operandTypes.get(0);
			final Type operand2Type = operandTypes.get(1);

			if (dateType.equals(operand1Type) || dateType.equals(operand2Type)) {
				result = JavaOperator.EQ_DATE;
			} else {
				result = OperatorMapping.operatorMapping.get(vbOperator);
			}
		} else if (VbOperator.LEQ.equals(vbOperator)) {
			final Type operand1Type = operandTypes.get(0);
			final Type operand2Type = operandTypes.get(1);

			if (dateType.equals(operand1Type) || dateType.equals(operand2Type)) {
				result = JavaOperator.LEQ_DATE;
			} else {
				result = OperatorMapping.operatorMapping.get(vbOperator);
			}
		} else if (VbOperator.LT.equals(vbOperator)) {
			final Type operand1Type = operandTypes.get(0);
			final Type operand2Type = operandTypes.get(1);

			if (dateType.equals(operand1Type) || dateType.equals(operand2Type)) {
				result = JavaOperator.LT_DATE;
			} else {
				result = OperatorMapping.operatorMapping.get(vbOperator);
			}
		} else if (VbOperator.GEQ.equals(vbOperator)) {
			final Type operand1Type = operandTypes.get(0);
			final Type operand2Type = operandTypes.get(1);

			if (dateType.equals(operand1Type) || dateType.equals(operand2Type)) {
				result = JavaOperator.GEQ_DATE;
			} else {
				result = OperatorMapping.operatorMapping.get(vbOperator);
			}
		} else if (VbOperator.GT.equals(vbOperator)) {
			final Type operand1Type = operandTypes.get(0);
			final Type operand2Type = operandTypes.get(1);

			if (dateType.equals(operand1Type) || dateType.equals(operand2Type)) {
				result = JavaOperator.GT_DATE;
			} else {
				result = OperatorMapping.operatorMapping.get(vbOperator);
			}
		} else {
			result = OperatorMapping.operatorMapping.get(vbOperator);
		}

		return result;
	}
}
