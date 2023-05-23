package io.proleap.vb6.transform.java.rules.lang.statement.chdir;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ChDirStmtContext;
import io.proleap.vb6.asg.metamodel.statement.chdir.ChDir;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ChdirRule extends VbTransformRule<ChDirStmtContext, ChDir> {

	@Override
	public void apply(final ChDirStmtContext ctx, final ChDir chDir, final RuleContext rc) {
		rc.p("ChDir(");
		rc.visitChildren(ctx);
		rc.p(");");
		rc.pNl(chDir);
	}

	@Override
	public Class<ChDirStmtContext> from() {
		return ChDirStmtContext.class;
	}
}
