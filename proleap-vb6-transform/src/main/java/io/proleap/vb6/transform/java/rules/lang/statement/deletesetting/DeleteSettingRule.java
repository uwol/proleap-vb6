package io.proleap.vb6.transform.java.rules.lang.statement.deletesetting;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.DeleteSettingStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.asg.metamodel.statement.deletesetting.DeleteSetting;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class DeleteSettingRule extends VbTransformRule<DeleteSettingStmtContext, DeleteSetting> {

	@Override
	public void apply(final DeleteSettingStmtContext ctx, final DeleteSetting deleteSetting, final RuleContext rc) {
		rc.p("DeleteSetting(");

		boolean firstEntry = true;

		for (final ValueStmtContext value : ctx.valueStmt()) {
			if (!firstEntry) {
				rc.p(", ");
			}

			rc.visit(value);

			firstEntry = false;
		}

		rc.p(");");
		rc.pNl(deleteSetting);
	}

	@Override
	public Class<DeleteSettingStmtContext> from() {
		return DeleteSettingStmtContext.class;
	}
}
