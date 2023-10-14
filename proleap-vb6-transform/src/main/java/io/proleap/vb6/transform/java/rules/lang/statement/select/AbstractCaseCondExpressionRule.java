package io.proleap.vb6.transform.java.rules.lang.statement.select;

import jakarta.inject.Singleton;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.metamodel.statement.select.Select;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCase;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.java.util.ClassUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public abstract class AbstractCaseCondExpressionRule<T extends ParserRuleContext>
		extends VbTransformRule<T, SelectCaseCondExpression> {

	protected void printApiEnumerationConstant(final ApiEnumerationConstantCall apiEnumerationConstantCall,
			final Type originalType, final Type typeToConvertTo, final RuleContext rc) {
		final ApiEnumerationConstant apiEnumerationConstant = apiEnumerationConstantCall.getApiEnumerationConstant();
		final ApiEnumeration apiEnumeration = apiEnumerationConstant.getApiEnumeration();

		final String apiEnumerationConstantName = apiEnumerationConstant.getName();
		final String apiEnumerationName = apiEnumeration.getName();

		printEnumerationConstant(apiEnumerationName, apiEnumerationConstantName, originalType, typeToConvertTo, rc);
	}

	protected void printEnumerationConstant(final EnumerationConstantCall enumerationConstantCall,
			final Type originalType, final Type typeToConvertTo, final RuleContext rc) {
		final EnumerationConstant enumerationConstant = enumerationConstantCall.getEnumerationConstant();
		final Enumeration enumeration = enumerationConstant.getEnumeration();

		final String enumerationConstantName = enumerationConstant.getName();
		final String apiEnumerationName = enumeration.getName();

		printEnumerationConstant(apiEnumerationName, enumerationConstantName, originalType, typeToConvertTo, rc);
	}

	protected void printEnumerationConstant(final String enumerationName, final String enumerationConstantName,
			final Type originalType, final Type typeToConvertTo, final RuleContext rc) {
		final String enumerationClassName = ClassUtils.getClassName(enumerationName);

		if (VbBaseType.INTEGER.equals(typeToConvertTo)) {
			rc.p("toInt(");
			rc.p("%s.%s", enumerationClassName, enumerationConstantName);
			rc.p(".getPosition())");
		} else if (VbBaseType.LONG.equals(typeToConvertTo)) {
			rc.p("%s.%s", enumerationClassName, enumerationConstantName);
			rc.p(".getPosition()");
		} else {
			rc.p("%s", enumerationConstantName);
		}
	}

	protected void printValueStmt(final ValueStmt valueStmt, final SelectCaseCondExpression selectCaseCondExpression,
			final RuleContext rc) {
		final Type selectCaseCondType = selectCaseCondExpression.getType();

		final SelectCase selectCase = selectCaseCondExpression.getSelectCaseCond().getSelectCase();
		final Select select = selectCase.getSelect();
		final Type selectType = select.getType();

		if (valueStmt instanceof CallValueStmt) {
			final CallValueStmt callValueStmt = (CallValueStmt) valueStmt;
			final Call call = callValueStmt.getCall();
			final CallType callType = call.getCallType();

			if (CallType.ENUMERATION_CONSTANT_CALL.equals(callType)) {
				final EnumerationConstantCall enumerationConstantCall = (EnumerationConstantCall) call.unwrap();

				printEnumerationConstant(enumerationConstantCall, selectCaseCondType, selectType, rc);
			} else if (CallType.API_ENUMERATION_CONSTANT_CALL.equals(callType)) {
				final ApiEnumerationConstantCall apiEnumerationConstantCall = (ApiEnumerationConstantCall) call
						.unwrap();

				printApiEnumerationConstant(apiEnumerationConstantCall, selectCaseCondType, selectType, rc);
			} else {
				rc.getTypedPrinter().printWithAdjustedType(valueStmt.getCtx(), selectCaseCondType, selectType);
			}
		} else {
			rc.visit(valueStmt.getCtx());
		}
	}
}
