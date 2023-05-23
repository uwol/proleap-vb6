package io.proleap.vb6.transform.java.rules.lang.statement.onerror;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.OnErrorStmtContext;
import io.proleap.vb6.asg.metamodel.statement.onerror.OnError;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class OnErrorRule extends VbTransformRule<OnErrorStmtContext, OnError> {

	@Override
	public void apply(final OnErrorStmtContext ctx, final OnError onError, final RuleContext rc) {

	}

	@Override
	public Class<OnErrorStmtContext> from() {
		return OnErrorStmtContext.class;
	}
}
