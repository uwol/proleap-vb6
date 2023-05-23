package io.proleap.vb6.transform.rule;

import org.antlr.v4.runtime.tree.ParseTree;

public interface VbTransformRuleMatcher {

	void apply(ParseTree ctx, Object asgElement, RuleContext ruleContext);

	void apply(ParseTree ctx, RuleContext ruleContext);

	void applyOnChildren(ParseTree ctx, RuleContext ruleContext);
}
