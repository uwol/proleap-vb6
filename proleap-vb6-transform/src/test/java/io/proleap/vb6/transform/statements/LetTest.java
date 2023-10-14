package io.proleap.vb6.transform.statements;

import java.io.File;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.proleap.vb6.transform.runner.VbTransformationTestRunner;

@MicronautTest
public class LetTest {

	@Inject
	private VbTransformationTestRunner runner;

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/transform/statements/Let.cls");
		runner.transformFile(inputFile);
	}
}