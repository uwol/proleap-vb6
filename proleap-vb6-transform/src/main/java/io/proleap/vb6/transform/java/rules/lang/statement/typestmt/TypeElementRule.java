package io.proleap.vb6.transform.java.rules.lang.statement.typestmt;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.TypeStmt_ElementContext;
import io.proleap.vb6.asg.metamodel.TypeElement;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.transform.java.type.JavaType;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class TypeElementRule extends VbTransformRule<TypeStmt_ElementContext, TypeElement> {

	@Override
	public void apply(final TypeStmt_ElementContext ctx, final TypeElement typeElement, final RuleContext rc) {
		final Type effectiveType = typeElement.getType();

		// in case of a regular variable
		if (!typeElement.isDeclaredAsArray()) {
			rc.p("%s", TypeMappingUtils.mapType(effectiveType));
		}
		// in case of an array typeElement
		else {
			if (typeElement.isDeclaredAsStaticArray()) {
				rc.p("%s", JavaType.STATIC_ARRAY);
			} else {
				rc.p("%s", JavaType.DYNAMIC_ARRAY);
			}

			// print generics
			rc.p("<");
			rc.p("%s", TypeMappingUtils.mapType(effectiveType));
			rc.p(">");
		}

		final String identifier = typeElement.getName();

		rc.p(" %s", identifier);

		// in case of a regular typeElement
		if (!typeElement.isDeclaredAsArray()) {
			// initialize the typeElement with null
			rc.p(" = null");
		}
		// in case of an array typeElement
		else {
			// initialize the array
			rc.p(" = new ");

			if (typeElement.isDeclaredAsStaticArray()) {
				rc.p("%s", JavaType.STATIC_ARRAY);
			} else {
				rc.p("%s", JavaType.DYNAMIC_ARRAY);
			}

			rc.p("<");
			rc.p("%s", TypeMappingUtils.mapType(effectiveType));
			rc.p(">");

			if (typeElement.isDeclaredAsStaticArray()) {
				rc.p("(");
				rc.visit(ctx.subscripts());
				rc.p(")");
			} else {
				rc.p("()");
			}
		}

		rc.p(";");
		rc.pNl(typeElement);
	}

	@Override
	public Class<TypeStmt_ElementContext> from() {
		return TypeStmt_ElementContext.class;
	}
}
