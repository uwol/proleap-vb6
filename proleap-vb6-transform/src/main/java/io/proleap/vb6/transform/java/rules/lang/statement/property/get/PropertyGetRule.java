package io.proleap.vb6.transform.java.rules.lang.statement.property.get;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.PropertyGetStmtContext;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.statement.property.get.PropertyGet;
import io.proleap.vb6.transform.java.rules.lang.AbstractProcedureRule;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class PropertyGetRule extends AbstractProcedureRule<PropertyGetStmtContext, PropertyGet> {

	@Override
	public void apply(final PropertyGetStmtContext ctx, final PropertyGet propertyGet, final RuleContext rc) {
		rc.pNl();

		final String identifier = propertyGet.getName();

		if (propertyGet.getPropertyGetCalls().isEmpty()) {
			printUnusedProcedureHeader(propertyGet, rc);
		}

		printSignatureHeader(propertyGet, rc);

		rc.p("(");

		if (ctx.argList() != null) {
			rc.visit(ctx.argList());
		}

		rc.p("){");
		rc.pNl(propertyGet);
		rc.getPrinter().indent();

		printReturnType(propertyGet.getType(), propertyGet.isDeclaredAsArray(), rc);

		rc.p(" ");

		rc.p("%s = null;", identifier);
		rc.pNl();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.p("return %s;", identifier);
		rc.pNl();

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		printSyntheticMethods(propertyGet, rc);
	}

	@Override
	public Class<PropertyGetStmtContext> from() {
		return PropertyGetStmtContext.class;
	}

	@Override
	protected void printSignatureHeader(final PropertyGet propertyGet, final RuleContext rc) {
		final String identifier = propertyGet.getName();
		final VisibilityEnum visibility = propertyGet.getVisibility();

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

		if (isJavaClassWithStaticMembers(propertyGet.getModule())) {
			rc.p("static ");
		}

		/*
		 * return type
		 */
		printReturnType(propertyGet.getType(), propertyGet.isDeclaredAsArray(), rc);
		rc.p(" ");

		/*
		 * name
		 */
		rc.p("%s", identifier);
	}
}
