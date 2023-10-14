package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import java.util.List;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsNotContext;
import io.proleap.vb6.asg.metamodel.valuestmt.EqValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.NotValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.EqValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.NotValueStmtImpl;
import io.proleap.vb6.transform.java.operator.JavaOperator;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsNotEqRule extends VsOperatorRule<VsNotContext, NotValueStmt> {

	@Override
	public void apply(final VsNotContext ctx, final NotValueStmt valueStmt, final RuleContext rc) {
		final List<ValueStmt> notOperands = valueStmt.getSubValueStmts();
		final ValueStmt notOperand = notOperands.get(0);

		final List<ValueStmt> eqOperands = notOperand.getSubValueStmts();
		final ValueStmt eqOperand1 = eqOperands.get(0);
		final ValueStmt eqOperand2 = eqOperands.get(1);

		printOperator(rc, JavaOperator.NEQ, eqOperand1.getCtx(), eqOperand2.getCtx());
	}

	@Override
	public Class<VsNotContext> from() {
		return VsNotContext.class;
	}

	@Override
	public Integer getPriority() {
		return 256;
	}

	@Override
	public boolean where(final VsNotContext ctx, final NotValueStmt valueStmt, final RuleContext rc) {
		final NotValueStmt pattern = new NotValueStmtImpl(valueStmt.getModule(), null, null);
		final EqValueStmt eq = new EqValueStmtImpl(valueStmt.getModule(), null, null);

		pattern.addSubValueStmt(eq);

		final boolean result = pattern.equals(valueStmt);
		return result;
	}
}
