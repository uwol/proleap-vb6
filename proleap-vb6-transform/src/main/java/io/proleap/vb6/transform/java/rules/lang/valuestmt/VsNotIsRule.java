package io.proleap.vb6.transform.java.rules.lang.valuestmt;

import java.util.List;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VsNotContext;
import io.proleap.vb6.asg.metamodel.valuestmt.IsValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.NotValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.IsValueStmtImpl;
import io.proleap.vb6.asg.metamodel.valuestmt.impl.NotValueStmtImpl;
import io.proleap.vb6.transform.java.operator.JavaOperator;
import io.proleap.vb6.transform.rule.RuleContext;

@Singleton
public class VsNotIsRule extends VsOperatorRule<VsNotContext, NotValueStmt> {

	@Override
	public void apply(final VsNotContext ctx, final NotValueStmt valueStmt, final RuleContext rc) {
		final List<ValueStmt> notOperands = valueStmt.getSubValueStmts();
		final ValueStmt notOperand = notOperands.get(0);

		final List<ValueStmt> isOperands = notOperand.getSubValueStmts();
		final ValueStmt isOperand1 = isOperands.get(0);
		final ValueStmt isOperand2 = isOperands.get(1);

		printOperator(rc, JavaOperator.NEQ, isOperand1.getCtx(), isOperand2.getCtx());
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
		final IsValueStmt is = new IsValueStmtImpl(valueStmt.getModule(), null, null);

		pattern.addSubValueStmt(is);

		final boolean result = pattern.equals(valueStmt);
		return result;
	}
}
