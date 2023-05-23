package io.proleap.vb6.transform.runner.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.core.util.StringUtils;
import io.proleap.vb6.asg.util.FilenameUtils;
import io.proleap.vb6.transform.java.runner.VbTransformationRunner;
import io.proleap.vb6.transform.runner.VbTransformationTestRunner;

@Singleton
public class VbTransformationTestRunnerImpl implements VbTransformationTestRunner {

	public final static String ARTIFACT_SUFFIX = ".java";

	private static final String JAVA_PACKAGE = "";

	private final static Logger LOG = LoggerFactory.getLogger(VbTransformationTestRunnerImpl.class);

	@Inject
	private VbTransformationRunner vbTransformationRunner;

	protected void doCompareArtifact(final File outputFile, final File inputFile) throws IOException {
		final String outputFileData = Files.readString(outputFile.toPath(), StandardCharsets.UTF_8);

		final File inputFileDirectory = inputFile.getParentFile();
		final String baseName = FilenameUtils.getBaseName(inputFile.getName());
		final File referenceOutputFile = new File(
				inputFileDirectory.getAbsolutePath() + "/" + baseName + ARTIFACT_SUFFIX);
		final String referenceOutputFileData = Files.readString(referenceOutputFile.toPath(), StandardCharsets.UTF_8);

		if (!StringUtils.isEmpty(referenceOutputFileData)) {
			LOG.info("Comparing artifact file with file {}.", referenceOutputFile.getName());

			assertEquals(referenceOutputFileData, outputFileData);
		} else {
			LOG.info("Ignoring empty artifact file {}.", referenceOutputFile.getName());
		}
	}

	@Override
	public void transformFile(final File inputFile) throws IOException {
		LOG.info("Transforming file {}.", inputFile.getName());

		final List<File> outputFiles = vbTransformationRunner.transformFile(inputFile, JAVA_PACKAGE);
		doCompareArtifact(outputFiles.get(0), inputFile);
	}
}
