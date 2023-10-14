package io.proleap.vb6.transform.java.rules.lang;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ArgContext;
import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ArgRule extends VbTransformRule<ArgContext, Arg> {

	@Override
	public void apply(final ArgContext ctx, final Arg arg, final RuleContext rc) {
		final Type effectiveType = arg.getType();
		rc.p("%s ", TypeMappingUtils.mapType(effectiveType));

		final String identifier = arg.getName();
		rc.p("%s", identifier);
	}

	@Override
	public Class<ArgContext> from() {
		return ArgContext.class;
	}
}
