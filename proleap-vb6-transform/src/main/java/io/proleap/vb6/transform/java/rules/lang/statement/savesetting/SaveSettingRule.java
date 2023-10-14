package io.proleap.vb6.transform.java.rules.lang.statement.savesetting;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.SaveSettingStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ValueStmtContext;
import io.proleap.vb6.asg.metamodel.statement.savesetting.SaveSetting;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class SaveSettingRule extends VbTransformRule<SaveSettingStmtContext, SaveSetting> {

	@Override
	public void apply(final SaveSettingStmtContext ctx, final SaveSetting saveSetting, final RuleContext rc) {
		rc.p("SaveSetting(");

		boolean firstEntry = true;

		for (final ValueStmtContext value : ctx.valueStmt()) {
			if (!firstEntry) {
				rc.p(", ");
			}

			rc.visit(value);

			firstEntry = false;
		}

		rc.p(");");
		rc.pNl(saveSetting);
	}

	@Override
	public Class<SaveSettingStmtContext> from() {
		return SaveSettingStmtContext.class;
	}
}
