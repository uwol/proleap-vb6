package io.proleap.vb6.transform.java.rules.lang.call;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_S_VariableOrProcedureCallContext;
import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationCall;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.EnumerationCall;
import io.proleap.vb6.asg.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.transform.java.util.ClassUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ICS_S_VariableOrProcedureCallRule extends VbTransformRule<ICS_S_VariableOrProcedureCallContext, Call> {

	@Override
	public void apply(final ICS_S_VariableOrProcedureCallContext ctx, final Call call, final RuleContext rc) {
		final String name = call.getName();
		final CallType callType = call.getCallType();

		if (CallType.MODULE_CALL.equals(callType)) {
			final String moduleName = name;
			final String clazzName = ClassUtils.getClassName(moduleName);
			rc.p("%s", clazzName);
		} else if (CallType.FUNCTION_CALL.equals(callType)) {
			final String functionName = name;
			rc.p("%s()", functionName);
		} else if (CallType.API_PROCEDURE_CALL.equals(callType)) {
			final String functionName = name;
			rc.p("%s()", functionName);
		} else if (CallType.PROPERTY_GET_CALL.equals(callType)) {
			final String propertyName = name;
			rc.p("%s()", propertyName);
		} else if (CallType.ENUMERATION_CALL.equals(callType)) {
			final EnumerationCall enumerationCall = (EnumerationCall) call.unwrap();
			final Enumeration enumeration = enumerationCall.getEnumeration();
			final String enumerationName = ClassUtils.getClassName(enumeration.getName());

			rc.p("%s", enumerationName);
		} else if (CallType.API_ENUMERATION_CALL.equals(callType)) {
			final ApiEnumerationCall apiEnumerationCall = (ApiEnumerationCall) call.unwrap();
			final ApiEnumeration apiEnumeration = apiEnumerationCall.getApiEnumeration();
			final String apiEnumerationName = ClassUtils.getClassName(apiEnumeration.getName());

			rc.p("%s", apiEnumerationName);
		} else if (CallType.ENUMERATION_CONSTANT_CALL.equals(callType)) {
			final EnumerationConstantCall enumerationConstantCall = (EnumerationConstantCall) call.unwrap();
			final EnumerationConstant enumerationConstant = enumerationConstantCall.getEnumerationConstant();
			final String enumerationConstantname = enumerationConstant.getName();

			final boolean isStandalone = enumerationConstantCall.isStandaloneCall();
			if (isStandalone) {
				final Enumeration enumeration = enumerationConstant.getEnumeration();
				final String enumerationName = ClassUtils.getClassName(enumeration.getName());
				rc.p("%s.", enumerationName);
			}

			rc.p("%s", enumerationConstantname);
		} else if (CallType.API_ENUMERATION_CONSTANT_CALL.equals(callType)) {
			final ApiEnumerationConstantCall apiEnumerationConstantCall = (ApiEnumerationConstantCall) call.unwrap();
			final ApiEnumerationConstant apiEnumerationConstant = apiEnumerationConstantCall
					.getApiEnumerationConstant();
			final String apiEnumerationConstantname = apiEnumerationConstant.getName();

			final boolean isStandalone = apiEnumerationConstantCall.isStandaloneCall();
			if (isStandalone) {
				final ApiEnumeration apiEnumeration = apiEnumerationConstant.getApiEnumeration();
				final String apiEnumerationName = ClassUtils.getClassName(apiEnumeration.getName());
				rc.p("%s.", apiEnumerationName);
			}

			rc.p("%s", apiEnumerationConstantname);
		} else if (CallType.ME_CALL.equals(callType)) {
			rc.p("this");
		} else {
			final String javaName = name;

			rc.p("%s", javaName);
		}
	}

	@Override
	public Class<ICS_S_VariableOrProcedureCallContext> from() {
		return ICS_S_VariableOrProcedureCallContext.class;
	}
}
