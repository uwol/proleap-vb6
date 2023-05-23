package io.proleap.vb6.analysis.util;

import java.util.stream.Stream;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.statement.constant.Constant;

public class VbStreamUtils {

	public static Stream<Constant> constants(final Module module) {
		return module.getConstants().stream();
	}

	public static Stream<Procedure> procedures(final Module module) {
		return module.getProcedures().stream();
	}

	public static Stream<Variable> variables(final Module module) {
		return module.getVariables().stream();
	}
}
