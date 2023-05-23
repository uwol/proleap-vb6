package io.proleap.vb6.transform.java.runner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import io.proleap.vb6.asg.params.VbParserParams;

public interface VbTransformationRunner {

	List<File> transformCode(String vbCode, String moduleName, String packageName, VbParserParams params)
			throws IOException;

	List<File> transformFile(File vbFile, String packageName) throws IOException;

	List<File> transformFile(File vbFile, String packageName, VbParserParams params) throws IOException;

	List<File> transformFiles(List<File> vbFiles, String packageName) throws IOException;

	List<File> transformFiles(List<File> vbFiles, String packageName, VbParserParams params) throws IOException;
}
