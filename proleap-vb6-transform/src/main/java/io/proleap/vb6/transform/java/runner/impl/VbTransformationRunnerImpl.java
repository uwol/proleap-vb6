package io.proleap.vb6.transform.java.runner.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.vb6.VisualBasic6Parser.ModuleContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.params.VbParserParams;
import io.proleap.vb6.asg.runner.impl.VbParserRunnerImpl;
import io.proleap.vb6.transform.java.printer.TypedPrinter;
import io.proleap.vb6.transform.java.printer.impl.TypedPrinterImpl;
import io.proleap.vb6.transform.java.runner.VbTransformationRunner;
import io.proleap.vb6.transform.java.util.ClassUtils;
import io.proleap.vb6.transform.printer.Printer;
import io.proleap.vb6.transform.printer.impl.PrinterImpl;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRuleMatcher;

@Singleton
public class VbTransformationRunnerImpl implements VbTransformationRunner {

	private final static Logger LOG = LoggerFactory.getLogger(VbTransformationRunnerImpl.class);

	@Inject
	protected VbTransformRuleMatcher ruleMatcher;

	protected List<File> transform(final Program program, final String packageName) throws IOException {
		final List<File> result = new ArrayList<>();

		for (final Module module : program.getModules()) {
			final String moduleName = module.getName();

			LOG.info("Transforming module {}.", moduleName);

			// open output file
			final File outputFile = Files.createTempFile(ClassUtils.getClassName(moduleName), "java").toFile();

			// rule context
			final RuleContext ruleContext = new RuleContext();

			// set output file on printer
			final Printer printer = new PrinterImpl(outputFile);

			// typed printer
			final TypedPrinter typedPrinter = new TypedPrinterImpl(ruleContext);

			// init rule context
			ruleContext.setProgram(program);
			ruleContext.setPackageName(packageName);
			ruleContext.setRuleMatcher(ruleMatcher);
			ruleContext.setPrinter(printer);
			ruleContext.setTypedPrinter(typedPrinter);

			// transform
			final ModuleContext ctx = module.getCtx();
			final Object asgElement = program.getASGElementRegistry().getASGElement(ctx);
			ruleMatcher.apply(ctx, asgElement, ruleContext);

			// close printer
			printer.flush();
			printer.close();

			result.add(outputFile);
		}

		return result;
	}

	@Override
	public List<File> transformCode(final String vbCode, final String moduleName, final String packageName,
			final VbParserParams params) throws IOException {
		final Program program = new VbParserRunnerImpl().analyzeCode(vbCode, moduleName, params);
		return transform(program, packageName);
	}

	@Override
	public List<File> transformFile(final File vbFile, final String packageName) throws IOException {
		return transformFiles(Arrays.asList(vbFile), packageName);
	}

	@Override
	public List<File> transformFile(final File vbFile, final String packageName, final VbParserParams params)
			throws IOException {
		return transformFiles(Arrays.asList(vbFile), packageName, params);
	}

	@Override
	public List<File> transformFiles(final List<File> vbFiles, final String packageName) throws IOException {
		final Program program = new VbParserRunnerImpl().analyzeFiles(vbFiles);
		return transform(program, packageName);
	}

	@Override
	public List<File> transformFiles(final List<File> vbFiles, final String packageName, final VbParserParams params)
			throws IOException {
		final Program program = new VbParserRunnerImpl().analyzeFiles(vbFiles, params);
		return transform(program, packageName);
	}
}
