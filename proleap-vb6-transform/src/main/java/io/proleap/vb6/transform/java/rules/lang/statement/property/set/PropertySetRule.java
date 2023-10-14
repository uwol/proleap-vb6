package io.proleap.vb6.transform.java.rules.lang.statement.property.set;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.PropertySetStmtContext;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.statement.property.set.PropertySet;
import io.proleap.vb6.transform.java.rules.lang.AbstractProcedureRule;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class PropertySetRule extends AbstractProcedureRule<PropertySetStmtContext, PropertySet> {

	@Override
	public void apply(final PropertySetStmtContext ctx, final PropertySet propertySet, final RuleContext rc) {
		rc.pNl();

		if (propertySet.getPropertySetCalls().isEmpty()) {
			printUnusedProcedureHeader(propertySet, rc);
		}

		printSignatureHeader(propertySet, rc);

		rc.p("(");

		if (ctx.argList() != null) {
			rc.visit(ctx.argList());
		}

		rc.p("){");
		rc.pNl(propertySet);
		rc.getPrinter().indent();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		printSyntheticMethods(propertySet, rc);
	}

	@Override
	public Class<PropertySetStmtContext> from() {
		return PropertySetStmtContext.class;
	}

	@Override
	protected void printSignatureHeader(final PropertySet propertySet, final RuleContext rc) {
		final String identifier = propertySet.getName();
		final VisibilityEnum visibility = propertySet.getVisibility();

		if (visibility == null) {
			rc.p("protected");
		} else {
			switch (visibility) {
			case PRIVATE:
				rc.p("private");
				break;
			default:
				rc.p("public");
				break;
			}
		}

		rc.p(" ");

		if (isJavaClassWithStaticMembers(propertySet.getModule())) {
			rc.p("static ");
		}

		/*
		 * name
		 */
		rc.p("void %s", identifier);
	}
}
