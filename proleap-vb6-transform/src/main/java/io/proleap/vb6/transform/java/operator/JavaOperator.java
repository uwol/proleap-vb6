package io.proleap.vb6.transform.java.operator;

public enum JavaOperator {
	AND("&&", true, true), DIV("/", true, true), EQ("==", true, true), EQ_DATE("isSameDay", false, true),
	GEQ(">=", true, true), GEQ_DATE("geq", false, true), GT(">", true, true), GT_DATE("gt", false, true),
	IMP("imp", false, false), IS("==", true, false), LEQ("<=", true, true), LEQ_DATE("leq", false, true),
	LIKE("like", false, false), LT("<", true, true), LT_DATE("lt", false, true), MINUS("-", true, true),
	MOD("%", true, true), MULT("*", true, true), NEQ("!=", true, true), NOT("!", true, false), OR("||", true, true),
	PLUS("+", true, true), POW("Math.pow", false, true), XOR("^", true, true);

	protected final boolean isInfix;

	protected final String operator;

	protected final boolean requiresSimilarPrimitiveTypes;

	private JavaOperator(final String operator, final boolean isInfix, final boolean requiresEqualTypes) {
		this.isInfix = isInfix;
		this.operator = operator;
		requiresSimilarPrimitiveTypes = requiresEqualTypes;
	}

	public String getOperator() {
		return operator;
	}

	public boolean isInfix() {
		return isInfix;
	}

	public boolean requiresSimilarPrimitiveTypes() {
		return requiresSimilarPrimitiveTypes;
	}

	@Override
	public String toString() {
		return operator;
	}
}