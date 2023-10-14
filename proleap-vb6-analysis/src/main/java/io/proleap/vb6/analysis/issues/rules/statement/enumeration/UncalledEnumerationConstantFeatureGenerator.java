package io.proleap.vb6.analysis.issues.rules.statement.enumeration;

import java.util.stream.Stream;

import jakarta.inject.Singleton;

import io.proleap.vb6.analysis.issues.rules.FeatureGenerator;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;

@Singleton
public class UncalledEnumerationConstantFeatureGenerator extends FeatureGenerator<EnumerationConstant> {

	@Override
	public Stream<EnumerationConstant> getAll(final Module module) {
		return module.getEnumerations().values().stream().flatMap(enumeration -> {
			return enumeration.getEnumerationConstants().values().stream();
		}).filter(enumerationConstant -> {
			return enumerationConstant.getEnumerationConstantCalls().isEmpty();
		});
	}
}
