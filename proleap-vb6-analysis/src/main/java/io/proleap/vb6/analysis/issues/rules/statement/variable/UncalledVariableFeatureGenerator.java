package io.proleap.vb6.analysis.issues.rules.statement.variable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.inject.Singleton;

import io.proleap.vb6.analysis.issues.rules.FeatureGenerator;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.Variable;

@Singleton
public class UncalledVariableFeatureGenerator extends FeatureGenerator<Variable> {

	protected List<Variable> collectVariablesInScope(final Scope scope, final Module module) {
		final List<Variable> result = new ArrayList<Variable>();

		result.addAll(getVariables(scope, module));

		for (final Scope subScope : scope.getSubScopes()) {
			result.addAll(collectVariablesInScope(subScope, module));
		}

		return result;
	}

	@Override
	public Stream<Variable> getAll(final Module module) {
		return collectVariablesInScope(module, module).stream();
	}

	protected List<Variable> getVariables(final Scope scope, final Module module) {
		final List<Variable> ctxs = scope.getVariables().stream().filter(variable -> {
			return variable.getVariableCalls().isEmpty();
		}).collect(Collectors.toList());

		return ctxs;
	}
}
