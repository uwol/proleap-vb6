package io.proleap.vb6.analysis.codexml;

import java.io.IOException;
import java.util.List;

import org.dom4j.Document;

import io.proleap.vb6.analysis.registry.VbIdRegistry;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;

public interface VbCodeXmlRunner {

	Document analyzeModule(Module module, VbIdRegistry idRegistry) throws IOException;

	List<Document> analyzeProgram(Program program, VbIdRegistry idRegistry) throws IOException;
}
