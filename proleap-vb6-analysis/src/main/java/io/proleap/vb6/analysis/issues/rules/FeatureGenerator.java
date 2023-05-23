package io.proleap.vb6.analysis.issues.rules;

import java.util.stream.Stream;

import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.metamodel.Module;

public abstract class FeatureGenerator<T extends ASGElement> {

	public abstract Stream<T> getAll(Module module);

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
