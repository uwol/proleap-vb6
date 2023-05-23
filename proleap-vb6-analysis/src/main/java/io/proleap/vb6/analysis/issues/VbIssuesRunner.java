package io.proleap.vb6.analysis.issues;

import io.proleap.vb6.analysis.issues.dto.IssuesDto;
import io.proleap.vb6.analysis.registry.VbIdRegistry;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Program;

public interface VbIssuesRunner {

	IssuesDto analyzeModule(Module module, VbIdRegistry idRegistry);

	IssuesDto analyzeProgram(Program program, VbIdRegistry idRegistry);
}
