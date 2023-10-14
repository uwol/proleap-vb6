package io.proleap.vb6.transform.java.rules.api;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_S_ProcedureOrArrayCallContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ArrayRule extends VbTransformRule<ICS_S_ProcedureOrArrayCallContext, Call> {

	@Override
	public void apply(final ICS_S_ProcedureOrArrayCallContext ctx, final Call call, final RuleContext rc) {
		final Type type = call.getType();

		rc.p("new %s[]", TypeMappingUtils.mapType(type));
		rc.p("{");

		rc.visitChildren(ctx);

		rc.p("}");
	}

	@Override
	public Class<ICS_S_ProcedureOrArrayCallContext> from() {
		return ICS_S_ProcedureOrArrayCallContext.class;
	}

	@Override
	public Integer getPriority() {
		return 256;
	}

	@Override
	public boolean where(final ICS_S_ProcedureOrArrayCallContext ctx, final Call call, final RuleContext rc) {
		final Call pattern = new UndefinedCallImpl("Array", null, call.getModule(), null, null);

		final boolean result = pattern.equals(call);
		return result;
	}
}
