package io.proleap.vb6.analysis.issues.statement.variable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;
import io.proleap.vb6.analysis.TestBase;
import io.proleap.vb6.analysis.issues.rules.statement.variable.UncalledVariableFeatureGenerator;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

@MicronautTest
public class UncalledVariableTest extends TestBase {

	@Inject
	private UncalledVariableFeatureGenerator feature;

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/vb6/analysis/issues/statement/variable/UncalledVariable.cls");
		final Program program = new VbParserRunnerImpl().analyzeFiles(Arrays.asList(inputFile));
		final Module module = program.getModule("UncalledVariable");
		final List<Variable> variables = feature.getAll(module).collect(Collectors.toList());

		assertEquals(1, variables.size());
		assertEquals("Var1", variables.get(0).getName());
	}
}
