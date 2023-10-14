package io.proleap.vb6.analysis.issues.rules.statement.enumeration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.analysis.issues.dto.IssueDto;
import io.proleap.vb6.analysis.issues.dto.SeverityEnum;
import io.proleap.vb6.analysis.issues.rules.IssueRule;
import io.proleap.vb6.analysis.registry.VbIdRegistry;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;

@Singleton
public class UncalledEnumerationConstantRule extends IssueRule {

	protected final String DESCRIPTION = "Enumeration constant %s is uncalled";

	@Inject
	private UncalledEnumerationConstantFeatureGenerator feature;

	@Override
	public List<IssueDto> apply(final Module module, final VbIdRegistry idRegistry) {
		final Stream<EnumerationConstant> enumerationConstants = feature.getAll(module);
		final List<ParserRuleContext> ctxs = enumerationConstants.map(enumerationConstant -> {
			return enumerationConstant.getCtx();
		}).collect(Collectors.toList());

		return entriesFromIds(ctxs, module, idRegistry, DESCRIPTION, SeverityEnum.MINOR);
	}
}
