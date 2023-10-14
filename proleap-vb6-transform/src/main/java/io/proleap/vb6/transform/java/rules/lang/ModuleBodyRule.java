package io.proleap.vb6.transform.java.rules.lang;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ModuleBodyContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ModuleBodyRule extends VbTransformRule<ModuleBodyContext, ASGElement> {

	@Override
	public void apply(final ModuleBodyContext ctx, final ASGElement arg, final RuleContext rc) {
		rc.visitChildren(ctx);
	}

	@Override
	public Class<ModuleBodyContext> from() {
		return ModuleBodyContext.class;
	}
}
