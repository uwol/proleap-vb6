package io.proleap.vb6.transform.java.rules.lang;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.BlockContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class BlockRule extends VbTransformRule<BlockContext, ASGElement> {

	@Override
	public void apply(final BlockContext ctx, final ASGElement asgElement, final RuleContext rc) {
		rc.visitChildren(ctx);
	}

	@Override
	public Class<BlockContext> from() {
		return BlockContext.class;
	}
}
