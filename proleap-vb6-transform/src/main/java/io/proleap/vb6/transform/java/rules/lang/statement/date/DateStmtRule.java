package io.proleap.vb6.transform.java.rules.lang.statement.date;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.DateStmtContext;
import io.proleap.vb6.asg.metamodel.statement.date.Date;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class DateStmtRule extends VbTransformRule<DateStmtContext, Date> {

	@Override
	public void apply(final DateStmtContext ctx, final Date date, final RuleContext rc) {
		rc.p("setDate(");
		rc.visit(ctx.valueStmt());
		rc.p(");");
		rc.pNl(date);
	}

	@Override
	public Class<DateStmtContext> from() {
		return DateStmtContext.class;
	}
}
