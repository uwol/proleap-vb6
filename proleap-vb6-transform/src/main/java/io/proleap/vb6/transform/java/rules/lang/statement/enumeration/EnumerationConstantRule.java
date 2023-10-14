package io.proleap.vb6.transform.java.rules.lang.statement.enumeration;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.EnumerationStmt_ConstantContext;
import io.proleap.vb6.asg.metamodel.statement.enumeration.EnumerationConstant;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class EnumerationConstantRule extends VbTransformRule<EnumerationStmt_ConstantContext, EnumerationConstant> {

	@Override
	public void apply(final EnumerationStmt_ConstantContext ctx, final EnumerationConstant enumerationConstant,
			final RuleContext rc) {
		final String identifier = enumerationConstant.getName();

		rc.p("%s(", identifier);

		if (ctx.valueStmt() != null) {
			rc.visit(ctx.valueStmt());
		} else {
			rc.p("%s", enumerationConstant.getPosition());
		}

		rc.p(")");
	}

	@Override
	public Class<EnumerationStmt_ConstantContext> from() {
		return EnumerationStmt_ConstantContext.class;
	}
}
