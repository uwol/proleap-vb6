package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsNewContext;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.valuestmt.NewValueStmt;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsNewRule extends VsOperatorRule<VsNewContext, NewValueStmt> {

	@Override
	public void apply(final VsNewContext ctx, final NewValueStmt newValueStmt, final RuleContext rc) {
		final Type type = newValueStmt.getType();

		rc.p("new %s()", TypeMappingUtils.mapType(type));
	}

	@Override
	public Class<VsNewContext> from() {
		return VsNewContext.class;
	}
}
