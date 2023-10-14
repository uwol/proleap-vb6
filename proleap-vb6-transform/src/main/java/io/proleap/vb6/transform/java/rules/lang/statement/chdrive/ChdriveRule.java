package io.proleap.vb6.transform.java.rules.lang.statement.chdrive;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ChDriveStmtContext;
import io.proleap.vb6.asg.metamodel.statement.chdrive.ChDrive;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ChdriveRule extends VbTransformRule<ChDriveStmtContext, ChDrive> {

	@Override
	public void apply(final ChDriveStmtContext ctx, final ChDrive chDrive, final RuleContext rc) {
		rc.p("ChDrive(");
		rc.visitChildren(ctx);
		rc.p(");");
		rc.pNl(chDrive);
	}

	@Override
	public Class<ChDriveStmtContext> from() {
		return ChDriveStmtContext.class;
	}
}
