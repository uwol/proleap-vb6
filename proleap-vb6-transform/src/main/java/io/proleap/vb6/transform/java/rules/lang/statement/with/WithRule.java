package io.proleap.vb6.transform.java.rules.lang.statement.with;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.WithStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.with.With;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class WithRule extends VbTransformRule<WithStmtContext, With> {

	public final static String WITH_CONTEXT_VAR_NAME = "withContextVar";

	@Override
	public void apply(final WithStmtContext ctx, final With with, final RuleContext rc) {
		final Call withVariableCall = with.getWithVariableCall();
		final Type withVariableCallType = withVariableCall.getType();

		rc.p("{");
		rc.pNl(with);
		rc.getPrinter().indent();

		rc.p("%s %s = ", TypeMappingUtils.mapType(withVariableCallType), WITH_CONTEXT_VAR_NAME);
		rc.visit(ctx.implicitCallStmt_InStmt());
		rc.p(";");
		rc.pNl();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<WithStmtContext> from() {
		return WithStmtContext.class;
	}
}
