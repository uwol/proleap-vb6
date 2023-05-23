package io.proleap.vb6.transform.rule.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.util.ANTLRUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;
import io.proleap.vb6.transform.rule.VbTransformRuleMatcher;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Singleton
public class VbTransformRuleMatcherImpl implements VbTransformRuleMatcher {

	private final static Logger LOG = LoggerFactory.getLogger(VbTransformRuleMatcherImpl.class);

	@Inject
	protected List<VbTransformRule> rules;

	@Override
	public void apply(final ParseTree ctx, final Object asgElement, final RuleContext ruleContext) {
		assert (ctx != null);

		if (ctx instanceof ParserRuleContext) {
			final ParserRuleContext parserRuleContext = (ParserRuleContext) ctx;

			final Class<? extends ParseTree> elementType = ctx.getClass();

			VbTransformRule<ParserRuleContext, ASGElement> matchingRule = null;

			// search the first applying rule
			for (final VbTransformRule rule : rules) {
				if (matchingRule != null) {
					break;
				} else {
					final Class<ParserRuleContext> from = rule.from();

					if (from.isAssignableFrom(elementType)) {
						final Boolean applies = rule.where(parserRuleContext, (ASGElement) asgElement, ruleContext);

						if (applies) {
							matchingRule = rule;
						}
					}
				}
			}

			if (matchingRule != null) {
				matchingRule.apply(parserRuleContext, (ASGElement) asgElement, ruleContext);
			} else {
				LOG.warn("missing rule for {}", elementType);
			}
		}
	}

	@Override
	public void apply(final ParseTree ctx, final RuleContext ruleContext) {
		final Object asgElement = ruleContext.getProgram().getASGElementRegistry().getASGElement(ctx);
		apply(ctx, asgElement, ruleContext);
	}

	@Override
	public void applyOnChildren(final ParseTree ctx, final RuleContext ruleContext) {
		final List<ParseTree> children = ANTLRUtils.findChildren(ctx);

		for (final ParseTree child : children) {
			apply(child, ruleContext);
		}
	}

	@PostConstruct
	public void init() {
		Collections.sort(rules);
		LOG.info("Initialized {} VB 6.0 transformation rules as {}.", rules.size(), rules);
	}
}
