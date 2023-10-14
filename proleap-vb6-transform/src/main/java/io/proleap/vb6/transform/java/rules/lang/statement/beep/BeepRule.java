package io.proleap.vb6.transform.java.rules.lang.statement.beep;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.BeepStmtContext;
import io.proleap.vb6.asg.metamodel.statement.beep.Beep;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class BeepRule extends VbTransformRule<BeepStmtContext, Beep> {

	@Override
	public void apply(final BeepStmtContext ctx, final Beep beep, final RuleContext rc) {
		rc.p("Beep();");
		rc.pNl(beep);
	}

	@Override
	public Class<BeepStmtContext> from() {
		return BeepStmtContext.class;
	}
}
