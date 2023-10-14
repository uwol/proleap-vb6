package io.proleap.vb6.analysis.issues.rules.statement.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.inject.Singleton;

import io.proleap.vb6.analysis.issues.rules.FeatureGenerator;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;

@Singleton
public class UncalledConstantFeatureGenerator extends FeatureGenerator<Constant> {

	protected List<Constant> collectConstantsInScope(final Scope scope, final Module module) {
		final List<Constant> result = new ArrayList<Constant>();

		result.addAll(getConstants(scope, module));

		for (final Scope subScope : scope.getSubScopes()) {
			result.addAll(collectConstantsInScope(subScope, module));
		}

		return result;
	}

	@Override
	public Stream<Constant> getAll(final Module module) {
		return collectConstantsInScope(module, module).stream();
	}

	protected List<Constant> getConstants(final Scope scope, final Module module) {
		final List<Constant> ctxs = scope.getConstants().stream().filter(variable -> {
			return variable.getConstantCalls().isEmpty();
		}).collect(Collectors.toList());

		return ctxs;
	}
}
