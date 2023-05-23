package io.proleap.vb6.transform.java.rules.lang.statement.constant;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ConstSubStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ConstSubRule extends VbTransformRule<ConstSubStmtContext, Constant> {

	@Override
	public void apply(final ConstSubStmtContext ctx, final Constant constant, final RuleContext rc) {
		final boolean isModuleConstant = constant.isModuleConstant();

		rc.p("final ");

		final Module module = constant.getModule();

		if (isModuleConstant && module.isModuleWithMetaData()) {
			final VisibilityEnum visibility = constant.getVisibility();

			if (visibility == null) {
				rc.p("protected ");
			} else {
				switch (visibility) {
				case PRIVATE:
					rc.p("private ");
					break;
				default:
					break;
				}
			}

			if (module instanceof StandardModule) {
				rc.p("static ");
			}
		}

		rc.p("%s ", TypeMappingUtils.mapType(constant.getType()));

		final String identifier = constant.getName();
		rc.p("%s = ", identifier);

		final Type leftHandType = constant.getType();
		final Type rightHandType = constant.getValueStmt().getType();

		rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(), rightHandType, leftHandType);

		rc.p(";");
		rc.pNl(constant);
		rc.pNl();
	}

	@Override
	public Class<ConstSubStmtContext> from() {
		return ConstSubStmtContext.class;
	}
}
