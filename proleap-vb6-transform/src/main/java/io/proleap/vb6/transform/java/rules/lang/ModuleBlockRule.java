package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ModuleBlockContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.util.ANTLRUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ModuleBlockRule extends VbTransformRule<ModuleBlockContext, ASGElement> {

	@Override
	public void apply(final ModuleBlockContext ctx, final ASGElement asgElement, final RuleContext rc) {
		final Module module = ANTLRUtils.findParent(Module.class, ctx, rc.getProgram().getASGElementRegistry());

		if (!module.isModuleWithMetaData()) {
			rc.p("static {");
			rc.pNl();
			rc.getPrinter().indent();
		}

		rc.visitChildren(ctx);

		if (!module.isModuleWithMetaData()) {
			rc.getPrinter().unindent();
			rc.p("}");
			rc.pNl();
		}
	}

	@Override
	public Class<ModuleBlockContext> from() {
		return ModuleBlockContext.class;
	}
}
