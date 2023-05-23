package io.proleap.vb6.transform.java.rules.lang.statement.declare;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.DeclareStmtContext;
import io.proleap.vb6.asg.metamodel.ProcedureDeclaration;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class DeclareRule extends VbTransformRule<DeclareStmtContext, ProcedureDeclaration> {

	@Override
	public void apply(final DeclareStmtContext ctx, final ProcedureDeclaration procedureDeclaration,
			final RuleContext rc) {
	}

	@Override
	public Class<DeclareStmtContext> from() {
		return DeclareStmtContext.class;
	}
}
