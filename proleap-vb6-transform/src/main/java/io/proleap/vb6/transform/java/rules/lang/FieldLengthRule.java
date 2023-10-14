package io.proleap.vb6.transform.java.rules.lang;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.FieldLengthContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class FieldLengthRule extends VbTransformRule<FieldLengthContext, ASGElement> {

	@Override
	public void apply(final FieldLengthContext ctx, final ASGElement asgElement, final RuleContext rc) {
		rc.visitChildren(ctx);
	}

	@Override
	public Class<FieldLengthContext> from() {
		return FieldLengthContext.class;
	}
}
