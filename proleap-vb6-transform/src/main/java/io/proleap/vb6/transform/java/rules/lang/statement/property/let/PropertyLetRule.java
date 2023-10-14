package io.proleap.vb6.transform.java.rules.lang.statement.property.let;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.PropertyLetStmtContext;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.statement.property.let.PropertyLet;
import io.proleap.vb6.transform.java.rules.lang.AbstractProcedureRule;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class PropertyLetRule extends AbstractProcedureRule<PropertyLetStmtContext, PropertyLet> {

	@Override
	public void apply(final PropertyLetStmtContext ctx, final PropertyLet propertyLet, final RuleContext rc) {
		rc.pNl();

		if (propertyLet.getPropertyLetCalls().isEmpty()) {
			printUnusedProcedureHeader(propertyLet, rc);
		}

		printSignatureHeader(propertyLet, rc);

		rc.p("(");

		if (ctx.argList() != null) {
			rc.visit(ctx.argList());
		}

		rc.p("){");
		rc.pNl(propertyLet);
		rc.getPrinter().indent();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		printSyntheticMethods(propertyLet, rc);
	}

	@Override
	public Class<PropertyLetStmtContext> from() {
		return PropertyLetStmtContext.class;
	}

	@Override
	protected void printSignatureHeader(final PropertyLet propertyLet, final RuleContext rc) {
		final String identifier = propertyLet.getName();
		final VisibilityEnum visibility = propertyLet.getVisibility();

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

		if (isJavaClassWithStaticMembers(propertyLet.getModule())) {
			rc.p("static ");
		}

		/*
		 * name
		 */
		rc.p("void %s", identifier);
	}
}
