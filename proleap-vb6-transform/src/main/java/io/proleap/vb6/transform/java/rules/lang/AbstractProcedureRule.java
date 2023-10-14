package io.proleap.vb6.transform.java.rules.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.inject.Singleton;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Arg;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.transform.java.type.JavaType;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public abstract class AbstractProcedureRule<S extends ParserRuleContext, T extends Procedure> extends VbTransformRule<S, T> {

	protected boolean isJavaClassWithStaticMembers(final Module module) {
		return module instanceof StandardModule || !module.isModuleWithMetaData();
	}

	protected void printReturnType(final Type type, final boolean isDeclaredAsArray, final RuleContext rc) {
		// in case of a regular type
		if (!isDeclaredAsArray) {
			rc.p("%s", TypeMappingUtils.mapType(type));
		}
		// in case of an array variable
		else {
			rc.p("%s", JavaType.DYNAMIC_ARRAY);

			// print generics
			rc.p("<");
			rc.p("%s", TypeMappingUtils.mapType(type));
			rc.p(">");
		}
	}

	protected abstract void printSignatureHeader(final T procedure, final RuleContext rc);

	protected void printSyntheticMethods(final T procedure, final RuleContext rc) {
		/*
		 * additional synthetic functions for default values
		 */
		final List<Arg> optionalArgs = procedure.getOptionalArgsList();
		final List<Arg> optionalArgsReverse = new ArrayList<Arg>(optionalArgs);

		Collections.reverse(optionalArgsReverse);

		// for each optional arg an individual synthetic method is
		// generated
		for (final Arg optionalArg : optionalArgsReverse) {
			/*
			 * method signature
			 */
			assert procedure != null : "unknown procedure element";
			assert procedure.getCtx() != null : "unknown context for procedure element " + procedure;

			rc.pNl();
			printSignatureHeader(procedure, rc);

			rc.p("(");

			boolean firstArgEntry = true;

			/*
			 * args until optional arg
			 */
			for (final Arg arg : procedure.getArgsList(optionalArg)) {
				if (!firstArgEntry) {
					rc.p(", ");
				}

				rc.visit(arg.getCtx());

				firstArgEntry = false;
			}

			rc.p(")");

			rc.p("{");
			rc.pNl();
			rc.getPrinter().indent();

			/*
			 * method body
			 */
			if (procedure.hasReturnType()) {
				rc.p("return ");
			}

			/*
			 * method call
			 */
			rc.p("%s(", procedure.getName());

			boolean firstCallArgEntry = true;
			boolean optionalCallArgHasBeenHit = false;

			// every synthetic method has to call the original method
			for (final Arg calledArg : procedure.getArgsList()) {
				if (!firstCallArgEntry) {
					rc.p(", ");
				}

				/*
				 * determine, whether the current arg is an optional one
				 */
				if (optionalArg.equals(calledArg)) {
					optionalCallArgHasBeenHit = true;
				}

				/*
				 * the arg for the original method can be given as an explicit parameter
				 */
				if (!optionalCallArgHasBeenHit) {
					rc.p("%s", calledArg.getName());
				}
				// or as a default value
				else if (calledArg.getCtx().argDefaultValue() != null) {
					final Call defaultValueCall = calledArg.getDefaultValueCall();
					final Type defaultValueCallType = defaultValueCall.getType();

					final Type argType = calledArg.getType();

					rc.getTypedPrinter().printWithAdjustedType(calledArg.getCtx().argDefaultValue(),
							defaultValueCallType, argType);
				} else {
					rc.p("null");
				}

				firstCallArgEntry = false;
			}

			rc.p(");");
			rc.pNl();

			rc.getPrinter().unindent();
			rc.p("}");
			rc.pNl();
			rc.pNl();
		}
	}

	protected void printUnusedProcedureHeader(final Procedure procedure, final RuleContext rc) {
		// rc.p("@Deprecated");
		// rc.pNl(procedure);
	}
}
