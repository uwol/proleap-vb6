package io.proleap.vb6.analysis.issues.rules.statement.variable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.analysis.issues.dto.IssueDto;
import io.proleap.vb6.analysis.issues.dto.SeverityEnum;
import io.proleap.vb6.analysis.issues.rules.IssueRule;
import io.proleap.vb6.analysis.registry.VbIdRegistry;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Variable;

@Singleton
public class UncalledVariableRule extends IssueRule {

	protected final String DESCRIPTION = "Variable %s is uncalled";

	@Inject
	private UncalledVariableFeatureGenerator feature;

	@Override
	public List<IssueDto> apply(final Module module, final VbIdRegistry idRegistry) {
		final Stream<Variable> variables = feature.getAll(module);
		final List<ParserRuleContext> ctxs = variables.map(variable -> {
			return variable.getCtx();
		}).collect(Collectors.toList());

		return entriesFromIds(ctxs, module, idRegistry, DESCRIPTION, SeverityEnum.MINOR);
	}
}
