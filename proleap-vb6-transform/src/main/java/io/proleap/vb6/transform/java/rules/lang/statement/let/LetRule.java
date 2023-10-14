package io.proleap.vb6.transform.java.rules.lang.statement.let;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.LetStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.statement.let.Let;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class LetRule extends VbTransformRule<LetStmtContext, Let> {

	@Override
	public void apply(final LetStmtContext ctx, final Let let, final RuleContext rc) {
		final Call leftHandCall = let.getLeftHandCall();
		final ValueStmt rightHandValueStmt = let.getRightHandValueStmt();
		final Type leftHandType = leftHandCall.getType();
		final Type rightHandType = rightHandValueStmt.getType();

		rc.visit(ctx.implicitCallStmt_InStmt());
		rc.p("=");
		rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(), rightHandType, leftHandType);
		rc.p(";");
		rc.pNl(let);
	}

	@Override
	public Class<LetStmtContext> from() {
		return LetStmtContext.class;
	}
}
