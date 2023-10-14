package io.proleap.vb6.transform.java.rules.lang.statement.deftype;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.DeftypeStmtContext;
import io.proleap.vb6.asg.metamodel.statement.deftype.Deftype;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class DefTypeRule extends VbTransformRule<DeftypeStmtContext, Deftype> {

	@Override
	public void apply(final DeftypeStmtContext ctx, final Deftype deftype, final RuleContext rc) {

	}

	@Override
	public Class<DeftypeStmtContext> from() {
		return DeftypeStmtContext.class;
	}
}
