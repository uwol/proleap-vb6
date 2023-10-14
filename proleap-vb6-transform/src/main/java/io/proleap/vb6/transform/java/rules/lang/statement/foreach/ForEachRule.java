package io.proleap.vb6.transform.java.rules.lang.statement.foreach;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ForEachStmtContext;
import io.proleap.vb6.asg.metamodel.statement.foreach.ElementVariable;
import io.proleap.vb6.asg.metamodel.statement.foreach.ForEach;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ForEachRule extends VbTransformRule<ForEachStmtContext, ForEach> {

	@Override
	public void apply(final ForEachStmtContext ctx, final ForEach forEach, final RuleContext rc) {
		final ElementVariable elementVariable = forEach.getElementVariable();

		@SuppressWarnings("unused")
		final String elementVariableTypeString;
		final String elementVariableName;

		if (elementVariable == null) {
			elementVariableTypeString = TypeMappingUtils.mapType(VbBaseType.VARIANT);
			elementVariableName = null;
		} else {
			elementVariableTypeString = TypeMappingUtils.mapType(elementVariable.getType());
			elementVariableName = elementVariable.getName();
		}

		rc.p("for(%s : ", elementVariableName);
		rc.visit(ctx.valueStmt());
		rc.p("){");
		rc.pNl(forEach);
		rc.getPrinter().indent();

		if (ctx.block() != null) {
			rc.visit(ctx.block());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();
	}

	@Override
	public Class<ForEachStmtContext> from() {
		return ForEachStmtContext.class;
	}

	@Override
	public Integer getPriority() {
		return -1;
	}
}
