package io.proleap.vb6.transform.java.rules.lang;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.LiteralContext;
import io.proleap.vb6.asg.metamodel.Literal;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class VbLiteralRule extends VbTransformRule<LiteralContext, Literal> {

	@Override
	public void apply(final LiteralContext ctx, final Literal literal, final RuleContext rc) {
		if (literal.getCtx().INTEGERLITERAL() != null) {
			final String text = literal.getCtx().INTEGERLITERAL().getText();
			final String cleanedNumberText = text.replace("#", "").replace("&", "");

			rc.p("%s", cleanedNumberText);
		} else if (literal.getCtx().DOUBLELITERAL() != null) {
			final String text = literal.getCtx().DOUBLELITERAL().getText();
			final String typeText = TypeMappingUtils.mapType(VbBaseType.DOUBLE);

			rc.p("new %s(%s)", typeText, text);
		} else if (literal.getCtx().STRINGLITERAL() != null) {
			final String str = literal.getCtx().STRINGLITERAL().getText();
			final String content = str.substring(1, str.length() - 1);

			final String escapedContent = escapeQuote(escapeBackslash(content));

			rc.p("\"%s\"", escapedContent);
		} else if (literal.getCtx().DATELITERAL() != null) {
			final String typeText = TypeMappingUtils.mapType(VbBaseType.DATE);

			rc.p("new %s(\"%s\")", typeText, literal.getCtx().DATELITERAL().getText());
		} else if (literal.getCtx().COLORLITERAL() != null) {
			final String typeText = TypeMappingUtils.mapType(VbBaseType.COLOR);

			rc.p("new %s(\"%s\")", typeText, literal.getCtx().COLORLITERAL().getText());
		} else if (literal.getCtx().FILENUMBER() != null) {
			final String typeText = TypeMappingUtils.mapType(VbBaseType.FILENUMBER);

			rc.p("new %s(\"%s\")", typeText, literal.getCtx().FILENUMBER().getText());
		} else if (literal.getCtx().NULL() != null) {
			rc.p("null");
		} else if (literal.getCtx().TRUE() != null) {
			rc.p("true");
		} else if (literal.getCtx().FALSE() != null) {
			rc.p("false");
		} else if (literal.getCtx().NOTHING() != null) {
			rc.p("null");
		}
	}

	protected String escapeBackslash(final String str) {
		final String result = str.replace("\\", "\\\\");
		return result;
	}

	protected String escapeQuote(final String str) {
		final String result = str.replaceAll("\"\"", "\\\\\"");
		return result;
	}

	@Override
	public Class<LiteralContext> from() {
		return LiteralContext.class;
	}
}
