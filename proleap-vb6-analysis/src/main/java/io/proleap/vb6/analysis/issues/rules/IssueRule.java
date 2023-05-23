package io.proleap.vb6.analysis.issues.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.analysis.issues.dto.IssueDto;
import io.proleap.vb6.analysis.issues.dto.SeverityEnum;
import io.proleap.vb6.analysis.registry.VbIdRegistry;
import io.proleap.vb6.analysis.util.NamingUtils;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.registry.ASGElementRegistry;

public abstract class IssueRule {

	public abstract List<IssueDto> apply(Module module, VbIdRegistry idRegistry);

	protected String context(final Module module) {
		return module.getName();
	}

	protected List<IssueDto> entries(final IssueDto entry) {
		return Arrays.asList(entry);
	}

	protected List<IssueDto> entriesFromIds(final List<ParserRuleContext> ctxs, final Module module,
			final VbIdRegistry idRegistry, final String message, final SeverityEnum severity) {
		final List<IssueDto> result;

		if (ctxs.isEmpty()) {
			result = null;
		} else {
			result = new ArrayList<IssueDto>();

			for (final ParserRuleContext ctx : ctxs) {
				final Program program = module.getProgram();
				final ASGElementRegistry asgElementRegistry = program.getASGElementRegistry();

				final String path = idRegistry.assureAbsoluteId(ctx, asgElementRegistry);
				final String name = NamingUtils.determineFullQualifiedName(ctx, program);

				final String text = String.format(message, name);
				final IssueDto entry = entry(context(module), text, severity, path);
				result.add(entry);
			}
		}

		return result;
	}

	protected IssueDto entry(final String context, final String description, final SeverityEnum severity,
			final String href) {
		return new IssueDto(context, description, severity, href);
	}

	protected String id(final ParserRuleContext ctx, final Module module, final VbIdRegistry idRegistry) {
		final ASGElementRegistry asgElementRegistry = module.getProgram().getASGElementRegistry();
		return idRegistry.assureAbsoluteId(ctx, asgElementRegistry);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
