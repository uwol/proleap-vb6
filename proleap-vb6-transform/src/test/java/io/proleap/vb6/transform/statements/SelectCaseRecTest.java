package io.proleap.vb6.transform.statements;

import java.io.File;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;
import io.proleap.vb6.transform.runner.VbTransformationTestRunner;

@MicronautTest
public class SelectCaseRecTest {

	@Inject
	private VbTransformationTestRunner runner;

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/transform/statements/SelectCaseRec.cls");
		runner.transformFile(inputFile);
	}
}