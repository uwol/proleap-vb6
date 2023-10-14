package io.proleap.vb6.analysis.issues.statement.procedure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.proleap.vb6.analysis.TestBase;
import io.proleap.vb6.analysis.issues.rules.statement.procedure.UncalledProcedureFeatureGenerator;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

@MicronautTest
public class UncalledProcedureTest extends TestBase {

	@Inject
	private UncalledProcedureFeatureGenerator feature;

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/vb6/analysis/issues/statement/procedure/UncalledProcedure.cls");
		final Program program = new VbParserRunnerImpl().analyzeFiles(Arrays.asList(inputFile));
		final Module module = program.getModule("UncalledProcedure");
		final List<Procedure> procedures = feature.getAll(module).collect(Collectors.toList());

		assertEquals(1, procedures.size());
		assertEquals("Mult", procedures.get(0).getName());
	}
}
