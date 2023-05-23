package io.proleap.vb6.analysis.issues.rules.statement.procedure;

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
import io.proleap.vb6.asg.metamodel.Procedure;

@Singleton
public class UncalledProcedureRule extends IssueRule {

	protected final String DESCRIPTION = "Procedure %s is uncalled";

	@Inject
	private UncalledProcedureFeatureGenerator feature;

	@Override
	public List<IssueDto> apply(final Module module, final VbIdRegistry idRegistry) {
		final Stream<Procedure> procedures = feature.getAll(module);
		final List<ParserRuleContext> ctxs = procedures.map(procedure -> {
			return procedure.getCtx();
		}).collect(Collectors.toList());

		return entriesFromIds(ctxs, module, idRegistry, DESCRIPTION, SeverityEnum.MINOR);
	}
}
