package io.proleap.vb6.analysis.issues.statement.enumerationconstant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;
import io.proleap.vb6.analysis.TestBase;
import io.proleap.vb6.analysis.issues.rules.statement.enumeration.UncalledEnumerationConstantFeatureGenerator;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

@MicronautTest
public class UncalledEnumerationConstantTest extends TestBase {

	@Inject
	private UncalledEnumerationConstantFeatureGenerator feature;

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/vb6/analysis/issues/statement/enumerationconstant/UncalledEnumerationConstant.cls");
		final Program program = new VbParserRunnerImpl().analyzeFiles(Arrays.asList(inputFile));
		final Module module = program.getModule("UncalledEnumerationConstant");
		final List<EnumerationConstant> enumerationConstants = feature.getAll(module).collect(Collectors.toList());

		assertEquals(7, enumerationConstants.size());
		assertEquals("Monday", enumerationConstants.get(0).getName());
	}
}
