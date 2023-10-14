package io.proleap.vb6.transform.java.rules.lang.statement.variable;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.VariableSubStmtContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.Variable;
import io.proleap.vb6.asg.metamodel.VisibilityEnum;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.transform.java.type.JavaType;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class VariableSubRule extends VbTransformRule<VariableSubStmtContext, Variable> {

	@Override
	public void apply(final VariableSubStmtContext ctx, final Variable variable, final RuleContext rc) {
		final Module module = variable.getModule();

		/*
		 * check, whether the variable is an instance variable, i.e. on the module level
		 */
		if (variable.isModuleVariable() && module.isModuleWithMetaData()) {
			final VisibilityEnum visibility = variable.getVisibility();

			if (visibility == null) {
				rc.p("protected ");
			} else {
				switch (visibility) {
				case PRIVATE:
					rc.p("private ");
					break;
				default:
					break;
				}
			}

			if (module instanceof StandardModule) {
				rc.p("static ");
			}
		}

		printType(variable, rc);

		final String identifier = variable.getName();

		rc.p(" %s = ", identifier);

		printInitialValue(ctx, variable, rc);

		rc.p(";");
		rc.pNl(variable);
	}

	@Override
	public Class<VariableSubStmtContext> from() {
		return VariableSubStmtContext.class;
	}

	private void printInitialValue(final VariableSubStmtContext ctx, final Variable variable, final RuleContext rc) {
		final Type type = variable.getType();

		// in case of a regular variable
		if (!variable.isDeclaredAsArray()) {
			// initialize the variable with null
			rc.p("null");
		}
		// in case of an array variable
		else {
			// initialize the array
			rc.p("new ");

			if (variable.isDeclaredAsStaticArray()) {
				rc.p("%s", JavaType.STATIC_ARRAY);
			} else {
				rc.p("%s", JavaType.DYNAMIC_ARRAY);
			}

			rc.p("<");
			rc.p("%s", TypeMappingUtils.mapType(type));
			rc.p(">");

			if (variable.isDeclaredAsStaticArray()) {
				rc.p("(");
				rc.visit(ctx.subscripts());
				rc.p(")");
			} else {
				rc.p("()");
			}
		}
	}

	private void printType(final Variable variable, final RuleContext rc) {
		final Type type = variable.getType();

		// in case of a regular variable
		if (!variable.isDeclaredAsArray() && !variable.isRedimed()) {
			rc.p("%s", TypeMappingUtils.mapType(type));
		}
		// in case of an array variable
		else {
			if (variable.isDeclaredAsStaticArray()) {
				rc.p("%s", JavaType.STATIC_ARRAY);
			} else {
				rc.p("%s", JavaType.DYNAMIC_ARRAY);
			}

			// print generics
			rc.p("<");
			rc.p("%s", TypeMappingUtils.mapType(type));
			rc.p(">");
		}
	}
}
