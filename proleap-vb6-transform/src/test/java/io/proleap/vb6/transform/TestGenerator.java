package io.proleap.vb6.transform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.ApplicationContext;
import io.proleap.vb6.asg.util.FilenameUtils;
import io.proleap.vb6.transform.java.runner.VbTransformationRunner;

public class TestGenerator {

	private final static String ARTIFACT_EXTENSION = ".java";

	private final static File INPUT_DIRECTORY = new File("src/test/resources");

	private static final String JAVA_EXTENSION = ".java";

	private static final String JAVA_PACKAGE = "";

	private final static Logger LOG = LoggerFactory.getLogger(TestGenerator.class);

	private final static File OUTPUT_DIRECTORY = new File("src/test/java");

	private final static String OUTPUT_FILE_SUFFIX = "Test";

	private final static boolean RENEW_ARTIFACT_FILE = false;

	protected static String firstToUpper(final String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	public static void generateArtifactFile(final File vbInputFile, final File outputDirectory,
			final ApplicationContext context) throws IOException {
		final String baseName = FilenameUtils.getBaseName(vbInputFile.getName());
		final File outputFile = new File(outputDirectory + "/" + baseName + ARTIFACT_EXTENSION);

		final boolean createdNewFile = outputFile.createNewFile();

		if (createdNewFile || RENEW_ARTIFACT_FILE) {
			LOG.info("Creating artifact file {}.", outputFile);

			final VbTransformationRunner vbTransformationRunner = context.getBean(VbTransformationRunner.class);
			final List<File> artifactFiles = vbTransformationRunner.transformFile(vbInputFile, JAVA_PACKAGE);
			Files.copy(artifactFiles.get(0).toPath(), outputFile.toPath());
		}
	}

	public static void generateTestClass(final File vb6InputFile, final File outputDirectory, final String packageName)
			throws IOException {
		final String inputFilename = getInputFilename(vb6InputFile);
		final File outputFile = new File(outputDirectory + "/" + inputFilename + OUTPUT_FILE_SUFFIX + JAVA_EXTENSION);

		final boolean createdNewFile = outputFile.createNewFile();

		if (createdNewFile) {
			LOG.info("Creating unit test {}.", outputFile);

			final PrintWriter pWriter = new PrintWriter(new FileWriter(outputFile));
			final String vb6InputFileName = vb6InputFile.getPath().replace("\\", "/");

			pWriter.write("package " + packageName + ";\n");
			pWriter.write("\n");
			pWriter.write("import java.io.File;\n");
			pWriter.write("\n");
			pWriter.write("import io.proleap.vb6.transform.TestBase;\n");
			pWriter.write("import org.junit.jupiter.api.Test;\n");
			pWriter.write("\n");
			pWriter.write("public class " + inputFilename + "Test extends TestBase {\n");
			pWriter.write("\n");
			pWriter.write("	@Test\n");
			pWriter.write("	public void test() throws Exception {\n");
			pWriter.write("		final File inputFile = new File(\"" + vb6InputFileName + "\");\n");
			pWriter.write("		runner.transformFile(inputFile);\n");
			pWriter.write("	}\n");
			pWriter.write("}");

			pWriter.flush();
			pWriter.close();
		}
	}

	public static void generateTestClasses(final File inputDirectory, final File outputDirectory,
			final String packageName, final ApplicationContext context) throws IOException {
		final String outputDirectoryPath = outputDirectory.getPath();

		if (inputDirectory.isDirectory()) {
			// for each of the files in the directory
			for (final File inputDirectoryFile : inputDirectory.listFiles()) {
				// if the file is a VB6 relevant file
				if (inputDirectoryFile.isFile() && !inputDirectoryFile.isHidden() && (isClazzModule(inputDirectoryFile)
						|| isStandardModule(inputDirectoryFile) || isForm(inputDirectoryFile))) {
					generateTestClass(inputDirectoryFile, outputDirectory, packageName);
					generateArtifactFile(inputDirectoryFile, inputDirectory, context);
				}
				// else, if the file is a relevant directory
				else if (inputDirectoryFile.isDirectory()) {
					final File subInputDirectory = inputDirectoryFile;
					final String subInputDirectoryName = subInputDirectory.getName();

					if (!".".equals(subInputDirectoryName) && !"..".equals(subInputDirectoryName)) {
						/*
						 * determine the output directory, where test classes should be placed
						 */
						final File subOutputDirectory = new File(outputDirectoryPath + "/" + subInputDirectoryName);
						subOutputDirectory.mkdirs();

						// determine the package name of test classes
						final String subPackageName = packageName == null || packageName.isEmpty()
								? subInputDirectoryName
								: packageName + "." + subInputDirectoryName;

						generateTestClasses(subInputDirectory, subOutputDirectory, subPackageName, context);
					}
				}
			}
		}
	}

	protected static String getInputFilename(final File inputFile) {
		final String result = firstToUpper(FilenameUtils.removeExtension(inputFile.getName()));
		return result;
	}

	protected static boolean isClazzModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "cls".equals(extension);
	}

	protected static boolean isForm(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "frm".equals(extension);
	}

	protected static boolean isStandardModule(final File inputFile) {
		final String extension = FilenameUtils.getExtension(inputFile.getName()).toLowerCase();
		return "bas".equals(extension);
	}

	public static void main(final String[] args) throws IOException {
		final ApplicationContext context = ApplicationContext.run();
		generateTestClasses(INPUT_DIRECTORY, OUTPUT_DIRECTORY, "", context);
	}
}
