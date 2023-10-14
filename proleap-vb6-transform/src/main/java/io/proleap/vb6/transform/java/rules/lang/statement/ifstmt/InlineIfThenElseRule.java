package io.proleap.vb6.transform.java.rules.lang.statement.ifstmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.InlineIfThenElseContext;
import io.proleap.vb6.asg.metamodel.statement.ifstmt.InlineIfThenElse;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class InlineIfThenElseRule extends VbTransformRule<InlineIfThenElseContext, InlineIfThenElse> {

	@Override
	public void apply(final InlineIfThenElseContext ctx, final InlineIfThenElse inlineIfThenElse,
			final RuleContext rc) {
		rc.p("if(");
		rc.visit(ctx.ifConditionStmt());
		rc.p("){");
		rc.pNl(inlineIfThenElse);
		rc.getPrinter().indent();

		if (ctx.blockStmt(0) != null) {
			rc.visit(ctx.blockStmt(0));
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		if (ctx.ELSE() != null) {
			rc.p("else{");
			rc.pNl();
			rc.getPrinter().indent();

			if (ctx.blockStmt(1) != null) {
				rc.visit(ctx.blockStmt(1));
			}

			rc.getPrinter().unindent();
			rc.p("}");
			rc.pNl();
		}
	}

	@Override
	public Class<InlineIfThenElseContext> from() {
		return InlineIfThenElseContext.class;
	}
}
