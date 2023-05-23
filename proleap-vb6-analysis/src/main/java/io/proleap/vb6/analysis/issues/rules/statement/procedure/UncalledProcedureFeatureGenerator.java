package io.proleap.vb6.analysis.issues.rules.statement.procedure;

import java.util.stream.Stream;

import javax.inject.Singleton;

import io.proleap.vb6.analysis.issues.rules.FeatureGenerator;
import io.proleap.vb6.analysis.util.VbStreamUtils;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Procedure;

@Singleton
public class UncalledProcedureFeatureGenerator extends FeatureGenerator<Procedure> {

	@Override
	public Stream<Procedure> getAll(final Module module) {
		return VbStreamUtils.procedures(module).filter(procedure -> {
			return procedure.getCalls().isEmpty();
		});
	}
}
