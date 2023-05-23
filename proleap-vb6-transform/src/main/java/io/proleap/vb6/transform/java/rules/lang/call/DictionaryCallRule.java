package io.proleap.vb6.transform.java.rules.lang.call;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.DictionaryCallStmtContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class DictionaryCallRule extends VbTransformRule<DictionaryCallStmtContext, Call> {

	@Override
	public void apply(final DictionaryCallStmtContext ctx, final Call call, final RuleContext rc) {
		final String identifier = call.getName();

		rc.p(".getDefaultProperty(%s)", identifier);
	}

	@Override
	public Class<DictionaryCallStmtContext> from() {
		return DictionaryCallStmtContext.class;
	}
}
