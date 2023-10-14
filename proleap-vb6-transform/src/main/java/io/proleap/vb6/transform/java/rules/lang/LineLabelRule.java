package io.proleap.vb6.transform.java.rules.lang;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.LineLabelContext;
import io.proleap.vb6.asg.metamodel.LineLabel;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class LineLabelRule extends VbTransformRule<LineLabelContext, LineLabel> {

	@Override
	public void apply(final LineLabelContext ctx, final LineLabel lineLabel, final RuleContext rc) {

	}

	@Override
	public Class<LineLabelContext> from() {
		return LineLabelContext.class;
	}
}
