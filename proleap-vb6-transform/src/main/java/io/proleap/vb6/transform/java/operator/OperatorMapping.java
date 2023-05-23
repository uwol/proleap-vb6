package io.proleap.vb6.transform.java.operator;

import java.util.HashMap;
import java.util.Map;

import io.proleap.vb6.asg.metamodel.Operator.VbOperator;

public class OperatorMapping {

	public static final Map<VbOperator, JavaOperator> operatorMapping = new HashMap<VbOperator, JavaOperator>();

	static {
		operatorMapping.put(VbOperator.AMP, JavaOperator.PLUS);
		operatorMapping.put(VbOperator.AND, JavaOperator.AND);
		operatorMapping.put(VbOperator.DIV, JavaOperator.DIV);
		operatorMapping.put(VbOperator.EQ, JavaOperator.EQ);
		operatorMapping.put(VbOperator.EQV, JavaOperator.EQ);
		operatorMapping.put(VbOperator.GEQ, JavaOperator.GEQ);
		operatorMapping.put(VbOperator.GT, JavaOperator.GT);
		operatorMapping.put(VbOperator.IMP, JavaOperator.IMP);
		operatorMapping.put(VbOperator.IS, JavaOperator.IS);
		operatorMapping.put(VbOperator.LEQ, JavaOperator.LEQ);
		operatorMapping.put(VbOperator.LIKE, JavaOperator.LIKE);
		operatorMapping.put(VbOperator.LT, JavaOperator.LT);
		operatorMapping.put(VbOperator.MINUS, JavaOperator.MINUS);
		operatorMapping.put(VbOperator.MOD, JavaOperator.MOD);
		operatorMapping.put(VbOperator.MULT, JavaOperator.MULT);
		operatorMapping.put(VbOperator.NEQ, JavaOperator.NEQ);
		operatorMapping.put(VbOperator.NOT, JavaOperator.NOT);
		operatorMapping.put(VbOperator.OR, JavaOperator.OR);
		operatorMapping.put(VbOperator.PLUS, JavaOperator.PLUS);
		operatorMapping.put(VbOperator.POW, JavaOperator.POW);
		operatorMapping.put(VbOperator.XOR, JavaOperator.XOR);
	}
}
