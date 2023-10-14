package io.proleap.vb6.transform.java.rules.lang.statement.sub;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.SubStmtContext;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.statement.sub.Sub;
import io.proleap.vb6.transform.java.rules.lang.AbstractProcedureRule;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class SubRule extends AbstractProcedureRule<SubStmtContext, Sub> {

	@Override
	public void apply(final SubStmtContext ctx, final Sub sub, final RuleContext rc) {
		rc.pNl();

		if (sub.getSubCalls().isEmpty()) {
			printUnusedProcedureHeader(sub, rc);
		}

		printSignatureHeader(sub, rc);

		rc.p("(");

		if (ctx.argList() != null) {
			rc.visit(ctx.argList());
		}

		rc.p("){");
		rc.pNl(sub);
		rc.getPrinter().indent();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		printSyntheticMethods(sub, rc);
	}

	@Override
	public Class<SubStmtContext> from() {
		return SubStmtContext.class;
	}

	@Override
	protected void printSignatureHeader(final Sub sub, final RuleContext rc) {
		final String identifier = sub.getName();
		final VisibilityEnum visibility = sub.getVisibility();

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

		if (isJavaClassWithStaticMembers(sub.getModule())) {
			rc.p("static ");
		}

		/*
		 * name
		 */
		rc.p("void %s", identifier);
	}
}
