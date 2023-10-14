package io.proleap.vb6.transform.java.rules.lang.statement.function;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.FunctionStmtContext;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.statement.function.Function;
import io.proleap.vb6.transform.java.rules.lang.AbstractProcedureRule;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class FunctionRule extends AbstractProcedureRule<FunctionStmtContext, Function> {

	@Override
	public void apply(final FunctionStmtContext ctx, final Function function, final RuleContext rc) {
		rc.pNl();

		final String identifier = function.getName();

		if (function.getFunctionCalls().isEmpty()) {
			printUnusedProcedureHeader(function, rc);
		}

		printSignatureHeader(function, rc);

		/*
		 * args
		 */
		rc.p("(");

		if (ctx.argList() != null) {
			rc.visit(ctx.argList());
		}

		rc.p("){");
		rc.pNl(function);
		rc.getPrinter().indent();

		/*
		 * print return variable
		 */
		printReturnType(function.getType(), function.isDeclaredAsArray(), rc);

		rc.p(" ");

		rc.p("%s = null;", identifier);
		rc.pNl();

		/*
		 * print method block
		 */
		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		/*
		 * return variable
		 */
		rc.p("return %s;", identifier);
		rc.pNl();

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		printSyntheticMethods(function, rc);
	}

	@Override
	public Class<FunctionStmtContext> from() {
		return FunctionStmtContext.class;
	}

	@Override
	protected void printSignatureHeader(final Function function, final RuleContext rc) {
		final String identifier = function.getName();
		final VisibilityEnum visibility = function.getVisibility();

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

		if (isJavaClassWithStaticMembers(function.getModule())) {
			rc.p("static ");
		}

		/*
		 * return type
		 */
		printReturnType(function.getType(), function.isDeclaredAsArray(), rc);
		rc.p(" ");

		/*
		 * name
		 */
		rc.p("%s", identifier);
	}
}
