package io.proleap.vb6.transform.java.rules.lang.statement.typestmt;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.TypeStmtContext;
import io.proleap.vb6.VisualBasic6Parser.TypeStmt_ElementContext;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.transform.java.util.ClassUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class TypeRule extends VbTransformRule<TypeStmtContext, io.proleap.vb6.asg.metamodel.Type> {

	@Override
	public void apply(final TypeStmtContext ctx, final io.proleap.vb6.asg.metamodel.Type type, final RuleContext rc) {
		final String identifier = type.getName();
		final String className = ClassUtils.getClassName(identifier);

		rc.pNl();

		final VisibilityEnum visibility = type.getVisibility();

		if (visibility == null) {
			rc.p("protected");
		} else {
			switch (visibility) {
			case PRIVATE:
				rc.p("private");
				break;
			default:
				rc.p("public");
				break;
			}
		}

		rc.p(" ");

		rc.p("class %s{", className);
		rc.pNl(type);
		rc.getPrinter().indent();

		for (final TypeStmt_ElementContext element : ctx.typeStmt_Element()) {
			rc.visit(element);
			rc.pNl();
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		rc.pNl();
	}

	@Override
	public Class<TypeStmtContext> from() {
		return TypeStmtContext.class;
	}
}
