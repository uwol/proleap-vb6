package io.proleap.vb6.transform.java.rules.lang.statement.foreach;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ForEachStmtContext;
import io.proleap.vb6.asg.metamodel.statement.foreach.ElementVariable;
import io.proleap.vb6.asg.metamodel.statement.foreach.ForEach;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ForEachRule_Iteratorless extends VbTransformRule<ForEachStmtContext, ForEach> {

	protected final static String counterVariableSuffix = "Counter";

	@Override
	public void apply(final ForEachStmtContext ctx, final ForEach forEach, final RuleContext rc) {
		final ElementVariable elementVariable = forEach.getElementVariable();

		final Type elementVariableType;
		final String elementVariableName;

		if (elementVariable == null) {
			elementVariableType = VbBaseType.VARIANT;
			elementVariableName = null;
		} else {
			elementVariableType = elementVariable.getType();
			elementVariableName = elementVariable.getName();
		}

		final String counter = elementVariableName + counterVariableSuffix;

		rc.p("for(int %s = 0; ", counter);
		rc.p("%s <= ", counter);
		rc.visit(ctx.valueStmt());
		rc.p(".size(); %s++", counter);
		rc.p(")");

		/*
		 * block
		 */

		rc.p("{");
		rc.pNl(forEach);
		rc.getPrinter().indent();

		rc.p("%s = ", elementVariableName);

		final String suffix = ".get(" + counter + ");";
		rc.getTypedPrinter().printWithAdjustedType(ctx.valueStmt(), suffix, VbBaseType.VARIANT, elementVariableType);

		rc.pNl();
		rc.pNl();

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
}
