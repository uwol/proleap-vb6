package io.proleap.vb6.transform.java.rules.lang.statement.resume;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ResumeStmtContext;
import io.proleap.vb6.asg.metamodel.statement.resume.Resume;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ResumeRule extends VbTransformRule<ResumeStmtContext, Resume> {

	@Override
	public void apply(final ResumeStmtContext ctx, final Resume resume, final RuleContext rc) {

	}

	@Override
	public Class<ResumeStmtContext> from() {
		return ResumeStmtContext.class;
	}
}
