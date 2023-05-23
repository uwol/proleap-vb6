package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ModuleBodyElementContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ModuleBodyElementRule extends VbTransformRule<ModuleBodyElementContext, ASGElement> {

	@Override
	public void apply(final ModuleBodyElementContext ctx, final ASGElement arg, final RuleContext rc) {
		rc.visitChildren(ctx);
	}

	@Override
	public Class<ModuleBodyElementContext> from() {
		return ModuleBodyElementContext.class;
	}
}
