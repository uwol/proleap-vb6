package io.proleap.vb6.transform.java.rules.lang.statement.select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.CaseCondExprIsContext;
import io.proleap.vb6.VisualBasic6Parser.CaseCondExprValueContext;
import io.proleap.vb6.VisualBasic6Parser.SelectCaseStmtContext;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.registry.ASGElementRegistry;
import io.proleap.vb6.asg.metamodel.statement.select.Select;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCase;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCond.SelectCaseCondType;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression;
import io.proleap.vb6.asg.metamodel.statement.select.SelectCaseCondExpression.SelectCaseCondExpressionType;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.vb6.asg.util.ANTLRUtils;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class SelectCaseRule_NonSwitchableType extends VbTransformRule<SelectCaseStmtContext, Select> {

	protected final String selectVariablePrefix = "selectVariable";

	@Override
	public void apply(final SelectCaseStmtContext ctx, final Select select, final RuleContext rc) {
		final ValueStmt valueStmt = select.getValueStmt();
		final Type type = valueStmt.getType();

		final int selectNumber = findSelectNumber(ctx, select.getModule().getProgram());
		final String selectVariableName = selectVariablePrefix + selectNumber;

		rc.p("%s %s = ", TypeMappingUtils.mapType(type), selectVariableName);

		rc.visit(ctx.valueStmt());

		rc.p(";");
		rc.pNl(select);

		boolean firstGroupEntry = true;

		// for each group of case statements before a block
		for (final List<SelectCase> selectCaseGroup : getSelectCaseGroups(select)) {
			final boolean isElseCaseGroup = isElseCaseGroup(selectCaseGroup);

			if (!firstGroupEntry) {
				rc.p("else ");
			}

			if (!isElseCaseGroup) {
				rc.p("if(");

				boolean firstCond = true;

				/*
				 * print the select case conditions
				 */
				for (final SelectCase selectCase : selectCaseGroup) {
					final SelectCaseCond selectCaseCond = selectCase.getSelectCaseCond();

					if (!firstCond) {
						rc.p(" || ");
					}

					for (final SelectCaseCondExpression selectCaseCondExpression : selectCaseCond
							.getSelectCaseCondExpressions()) {
						if (SelectCaseCondExpressionType.IS
								.equals(selectCaseCondExpression.getSelectCaseCondExpressionType())) {
							final CaseCondExprIsContext scCondIs = (CaseCondExprIsContext) selectCaseCondExpression
									.getCtx();

							rc.p("%s.equals(", selectVariableName);
							rc.visit(scCondIs.valueStmt());
							rc.p(")");
						} else if (SelectCaseCondExpressionType.VALUE
								.equals(selectCaseCondExpression.getSelectCaseCondExpressionType())) {
							final CaseCondExprValueContext scCondValue = (CaseCondExprValueContext) selectCaseCondExpression
									.getCtx();

							rc.p("%s.equals(", selectVariableName);
							rc.visit(scCondValue.valueStmt());
							rc.p(")");
						} else if (SelectCaseCondExpressionType.TO
								.equals(selectCaseCondExpression.getSelectCaseCondExpressionType())) {
							rc.p("<not implemented>");
						}
					}

					firstCond = false;
				}

				rc.p(")");
			}

			/*
			 * print the group block
			 */
			final SelectCase lastSelectCaseInGroup = selectCaseGroup.get(selectCaseGroup.size() - 1);

			if (lastSelectCaseInGroup.hasBlock()) {
				rc.p("{");
				rc.pNl();
				rc.getPrinter().indent();

				rc.visit(lastSelectCaseInGroup.getCtx().block());

				rc.getPrinter().unindent();
				rc.p("}");
				rc.pNl();
			}

			firstGroupEntry = false;
		}
	}

	protected int findSelectNumber(final SelectCaseStmtContext ctx, final Program program) {
		final ASGElementRegistry registry = program.getASGElementRegistry();
		final Collection<Select> parentSelects = ANTLRUtils.findAncestors(Select.class, ctx, registry);

		final int numberOfParentSelects = parentSelects.size();
		return numberOfParentSelects;
	}

	@Override
	public Class<SelectCaseStmtContext> from() {
		return SelectCaseStmtContext.class;
	}

	@Override
	public Integer getPriority() {
		return 256;
	}

	private List<List<SelectCase>> getSelectCaseGroups(final Select select) {
		final List<List<SelectCase>> result = new ArrayList<List<SelectCase>>();

		List<SelectCase> currentGroup = new ArrayList<SelectCase>();

		// for all cases of the select
		for (final SelectCase selectCase : select.getSelectCases()) {
			currentGroup.add(selectCase);

			if (selectCase.hasBlock()) {
				result.add(currentGroup);
				currentGroup = new ArrayList<SelectCase>();
			}
		}

		return result;
	}

	private boolean isElseCaseGroup(final List<SelectCase> caseGroup) {
		boolean result = false;

		for (final SelectCase selectCase : caseGroup) {
			final SelectCaseCondType selectCaseCondType = selectCase.getSelectCaseCond().getSelectCaseCondType();
			final boolean isElseSelectCase = SelectCaseCondType.ELSE.equals(selectCaseCondType);

			if (isElseSelectCase) {
				result = true;
				break;
			}
		}

		return result;
	}

	@Override
	public boolean where(final SelectCaseStmtContext ctx, final Select select, final RuleContext rc) {
		final Type type = select.getType();

		return VbBaseType.STRING.equals(type) || VbBaseType.DOUBLE.equals(type) || VbBaseType.COLLECTION.equals(type)
				|| VbBaseType.VARIANT.equals(type);
	}
}
