package io.proleap.vb6.analysis.complete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import jakarta.inject.Inject;

import org.dom4j.Document;
import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.proleap.vb6.analysis.TestBase;
import io.proleap.vb6.analysis.codexml.VbCodeXmlRunner;
import io.proleap.vb6.analysis.registry.VbIdRegistry;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;

@MicronautTest
public class ExampleTest extends TestBase {

	@Inject
	private VbCodeXmlRunner vbCodeXmlRunner;

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/vb6/analysis/complete/Example.bas");
		final Program program = new VbParserRunnerImpl().analyzeFiles(Arrays.asList(inputFile));

		final VbIdRegistry idRegistry = new VbIdRegistry();
		final List<Document> documents = vbCodeXmlRunner.analyzeProgram(program, idRegistry);
		assertNotNull(documents);
		assertFalse(documents.isEmpty());
	}
}