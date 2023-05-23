package io.proleap.vb6.transform.runner;

import java.io.File;
import java.io.IOException;

/**
 * VB6 transform runner for JUnit tests.
 */
public interface VbTransformationTestRunner {

	void transformFile(File inputFile) throws IOException;
}
