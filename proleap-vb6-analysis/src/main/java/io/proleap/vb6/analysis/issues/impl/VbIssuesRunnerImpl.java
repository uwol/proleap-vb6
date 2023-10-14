package io.proleap.vb6.analysis.issues.impl;

import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.vb6.analysis.issues.VbIssuesRunner;
import io.proleap.vb6.analysis.issues.dto.IssueDto;
import io.proleap.vb6.analysis.issues.dto.IssuesDto;
import io.proleap.vb6.analysis.issues.rules.IssueRule;
import io.proleap.vb6.analysis.registry.VbIdRegistry;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;

@Singleton
public class VbIssuesRunnerImpl implements VbIssuesRunner {

	private final static Logger LOG = LoggerFactory.getLogger(VbIssuesRunnerImpl.class);

	@Inject
	private List<IssueRule> rules;

	@Override
	public IssuesDto analyzeModule(final Module module, final VbIdRegistry idRegistry) {
		return applyRules(module, idRegistry);
	}

	@Override
	public IssuesDto analyzeProgram(final Program program, final VbIdRegistry idRegistry) {
		final IssuesDto result = new IssuesDto();

		for (final Module module : program.getModules()) {
			final IssuesDto issuesDto = analyzeModule(module, idRegistry);

			if (issuesDto != null) {
				result.issues.addAll(issuesDto.issues);
			}
		}

		return result;
	}

	protected IssuesDto applyRules(final Module module, final VbIdRegistry idRegistry) {
		final IssuesDto result = new IssuesDto();

		for (final IssueRule rule : rules) {
			final List<IssueDto> entries = rule.apply(module, idRegistry);

			if (entries != null) {
				result.issues.addAll(entries);
			}
		}

		return result;
	}

	@PostConstruct
	public void init() {
		LOG.info("Initialized {} VB 6.0 issues rules as {}.", rules.size(), rules);
	}
}
