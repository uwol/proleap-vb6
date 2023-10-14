package io.proleap.vb6.transform.java.rules.lang.statement.attribute;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.AttributeStmtContext;
import io.proleap.vb6.asg.metamodel.Attribute;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class AttributeRule extends VbTransformRule<AttributeStmtContext, Attribute> {

	@Override
	public void apply(final AttributeStmtContext ctx, final Attribute attribute, final RuleContext rc) {
		rc.p("%s ", TypeMappingUtils.mapType(attribute.getType()));

		final String identifier = attribute.getName();
		rc.p("%s = ", identifier);

		rc.visit(ctx.literal(0));
		rc.p(";");
		rc.pNl(attribute);
	}

	@Override
	public Class<AttributeStmtContext> from() {
		return AttributeStmtContext.class;
	}
}
