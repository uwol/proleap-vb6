package io.proleap.vb6.transform.java.rules.lang.statement.enumeration;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmtContext;
import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.transform.java.util.ClassUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class EnumerationRule extends VbTransformRule<EnumerationStmtContext, Enumeration> {

	@Override
	public void apply(final EnumerationStmtContext ctx, final Enumeration enumeration, final RuleContext rc) {
		final String identifier = enumeration.getName();
		final String enumClazzName = ClassUtils.getClassName(identifier);

		rc.p("public enum %s implements VbValueEnum{", enumClazzName);
		rc.pNl(enumeration);
		rc.getPrinter().indent();

		boolean firstEntry = true;

		for (final EnumerationStmt_ConstantContext ec : ctx.enumerationStmt_Constant()) {
			if (!firstEntry) {
				rc.p(", ");
			}

			rc.visit(ec);

			firstEntry = false;
		}

		rc.p(";");
		rc.pNl();
		rc.pNl();
		rc.p("private long position;");
		rc.pNl();
		rc.pNl();

		rc.p("private %s(final long position){", enumClazzName);
		rc.pNl();
		rc.getPrinter().indent();

		rc.p("this.position = position;");
		rc.pNl();

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		rc.pNl();

		rc.p("public long getPosition(){");
		rc.pNl();
		rc.getPrinter().indent();

		rc.p("return this.position;");
		rc.pNl();

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		rc.pNl();
	}

	@Override
	public Class<EnumerationStmtContext> from() {
		return EnumerationStmtContext.class;
	}
}
