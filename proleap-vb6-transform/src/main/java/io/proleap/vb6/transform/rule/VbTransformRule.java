package io.proleap.vb6.transform.rule;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.ASGElement;

public abstract class VbTransformRule<S extends ParserRuleContext, T extends ASGElement> implements Comparable<VbTransformRule<S, T>> {

	public abstract void apply(S ctx, T asgElement, RuleContext ruleContext);

	@Override
	public int compareTo(final VbTransformRule<S, T> r) {
		return r.getPriority().compareTo(this.getPriority());
	}

	public abstract Class<S> from();

	public Integer getPriority() {
		return 0;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	public boolean where(final S ctx, final T asgElement, final RuleContext rc) {
		return true;
	}
}
