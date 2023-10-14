package io.proleap.vb6.transform.java.rules.lang;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ArgDefaultValueContext;
import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.api.ApiEnumerationConstant;
import io.proleap.vb6.asg.metamodel.call.ApiEnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.Call.CallType;
import io.proleap.vb6.asg.metamodel.call.EnumerationConstantCall;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.transform.java.util.ClassUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ArgDefaultValueRule extends VbTransformRule<ArgDefaultValueContext, Call> {

	@Override
	public void apply(final ArgDefaultValueContext ctx, final Call call, final RuleContext rc) {
		final CallType callType = call.getCallType();

		if (CallType.ENUMERATION_CONSTANT_CALL.equals(callType)) {
			final EnumerationConstantCall enumerationConstantCall = (EnumerationConstantCall) call.unwrap();

			final EnumerationConstant enumerationConstant = enumerationConstantCall.getEnumerationConstant();
			final Enumeration enumeration = enumerationConstant.getEnumeration();

			final String enumerationName = ClassUtils.getClassName(enumeration.getName());
			final String enumerationConstantname = enumerationConstant.getName();

			rc.p("%s.%s", enumerationName, enumerationConstantname);
		} else if (CallType.API_ENUMERATION_CONSTANT_CALL.equals(callType)) {
			final ApiEnumerationConstantCall apiEnumerationConstantCall = (ApiEnumerationConstantCall) call.unwrap();

			final ApiEnumerationConstant apiEnumerationConstant = apiEnumerationConstantCall
					.getApiEnumerationConstant();
			final ApiEnumeration apiEnumeration = apiEnumerationConstant.getApiEnumeration();

			final String apiEnumerationName = ClassUtils.getClassName(apiEnumeration.getName());
			final String apiEnumerationConstantname = apiEnumerationConstant.getName();

			rc.p("%s.%s", apiEnumerationName, apiEnumerationConstantname);
		} else {
			rc.visitChildren(ctx);
		}

	}

	@Override
	public Class<ArgDefaultValueContext> from() {
		return ArgDefaultValueContext.class;
	}
}
