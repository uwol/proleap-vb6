package io.proleap.vb6.analysis.codexml.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import io.proleap.vb6.analysis.codexml.VbCodeXmlRunner;
import io.proleap.vb6.analysis.codexml.VbCodeXmlVisitor;
import io.proleap.vb6.analysis.registry.VbIdRegistry;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;

@Singleton
public class VbCodeXmlRunnerImpl implements VbCodeXmlRunner {

	@Override
	public Document analyzeModule(final Module module, final VbIdRegistry idRegistry) throws IOException {
		final Document document = DocumentHelper.createDocument();
		document.setXMLEncoding(StandardCharsets.UTF_8.toString());
		document.setName(module.getName());

		final VbCodeXmlVisitor visitor = new VbCodeXmlVisitor(module, document, idRegistry);
		visitor.visit(module.getCtx());

		return document;
	}

	@Override
	public List<Document> analyzeProgram(final Program program, final VbIdRegistry idRegistry) throws IOException {
		final List<Document> result = new ArrayList<Document>();

		for (final Module module : program.getModules()) {
			final Document document = analyzeModule(module, idRegistry);
			result.add(document);
		}

		return result;
	}
}
