package io.proleap.vb6.transform.java.printer.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.asg.metamodel.api.ApiEnumeration;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.asg.metamodel.type.Type;
import io.proleap.vb6.asg.metamodel.type.VbBaseType;
import io.proleap.vb6.transform.java.printer.TypedPrinter;
import io.proleap.vb6.transform.java.util.TypeMappingUtils;
import io.proleap.vb6.transform.rule.RuleContext;

public class TypedPrinterImpl implements TypedPrinter {

	protected class Printable {

		protected final ParseTree ctx;

		protected final String suffix;

		public Printable(final ParseTree ctx) {
			this.ctx = ctx;
			suffix = null;
		}

		public Printable(final ParseTree ctx, final String suffix) {
			this.ctx = ctx;
			this.suffix = suffix;
		}

		public String getString() {
			return ctx.getText();
		}

		public void print() {
			rc.visit(ctx);

			if (suffix != null) {
				rc.p(suffix);
			}
		}
	}

	protected final RuleContext rc;

	public TypedPrinterImpl(final RuleContext rc) {
		this.rc = rc;
	}

	@Override
	public void printWithAdjustedType(final ParseTree ctx, final String suffix, final Type originalType,
			final Type typeToConvertTo) {
		final Printable printable = new Printable(ctx, suffix);

		printWithAdjustedType(printable, originalType, typeToConvertTo);
	}

	@Override
	public void printWithAdjustedType(final ParseTree ctx, final Type originalType, final Type typeToConvertTo) {
		final Printable printable = new Printable(ctx);

		printWithAdjustedType(printable, originalType, typeToConvertTo);
	}

	protected void printWithAdjustedType(final Printable printable, final Type originalType,
			final Type typeToConvertTo) {

		final boolean isNull = printable.getString().toLowerCase().equals("null");

		// null value
		if (isNull) {
			printable.print();
		}
		// same types
		else if (originalType == typeToConvertTo) {
			printable.print();
		}
		// enumeration constant ---> INTEGER
		else if ((originalType instanceof Enumeration || originalType instanceof ApiEnumeration)
				&& VbBaseType.INTEGER.equals(typeToConvertTo)) {
			rc.p("toInt(");
			printable.print();
			rc.p(".getPosition())");
		}
		// enumeration constant ---> LONG
		else if ((originalType instanceof Enumeration || originalType instanceof ApiEnumeration)
				&& VbBaseType.LONG.equals(typeToConvertTo)) {
			printable.print();
			rc.p(".getPosition()");
		}
		// enumeration constant ---> STRING
		else if ((originalType instanceof Enumeration || originalType instanceof ApiEnumeration)
				&& VbBaseType.STRING.equals(typeToConvertTo)) {
			printable.print();
			rc.p(".toString()");
		}
		// BOOLEAN ---> INTEGER
		else if (VbBaseType.BOOLEAN.equals(originalType) && VbBaseType.INTEGER.equals(typeToConvertTo)) {
			rc.p("toInt(");
			printable.print();
			rc.p(")");
		}
		// BOOLEAN ---> LONG
		else if (VbBaseType.BOOLEAN.equals(originalType) && VbBaseType.LONG.equals(typeToConvertTo)) {
			rc.p("toLong(");
			printable.print();
			rc.p(")");
		}
		// BOOLEAN ---> STRING
		else if (VbBaseType.BOOLEAN.equals(originalType) && VbBaseType.STRING.equals(typeToConvertTo)) {
			rc.p("String.valueOf(");
			printable.print();
			rc.p(")");
		}
		// DOUBLE ---> INTEGER
		else if (VbBaseType.DOUBLE.equals(originalType) && VbBaseType.INTEGER.equals(typeToConvertTo)) {
			rc.p("toInt(");
			printable.print();
			rc.p(")");
		}
		// DOUBLE ---> LONG
		else if (VbBaseType.DOUBLE.equals(originalType) && VbBaseType.LONG.equals(typeToConvertTo)) {
			rc.p("toLong(");
			printable.print();
			rc.p(")");
		}
		// DOUBLE ---> STRING
		else if (VbBaseType.DOUBLE.equals(originalType) && VbBaseType.STRING.equals(typeToConvertTo)) {
			rc.p("String.valueOf(");
			printable.print();
			rc.p(")");
		}
		// INTEGER ---> BOOLEAN
		else if (VbBaseType.INTEGER.equals(originalType) && VbBaseType.BOOLEAN.equals(typeToConvertTo)) {
			rc.p("toBool(");
			printable.print();
			rc.p(")");
		}
		// INTEGER ---> INTEGER
		else if (VbBaseType.INTEGER.equals(originalType) && VbBaseType.LONG.equals(typeToConvertTo)) {
			rc.p("(long) ");
			printable.print();
		}
		// INTEGER ---> DOUBLE
		else if (VbBaseType.INTEGER.equals(originalType) && VbBaseType.DOUBLE.equals(typeToConvertTo)) {
			rc.p("(double) ");
			printable.print();
		}
		// INTEGER ---> STRING
		else if (VbBaseType.INTEGER.equals(originalType) && VbBaseType.STRING.equals(typeToConvertTo)) {
			rc.p("String.valueOf(");
			printable.print();
			rc.p(")");
		}
		// INTEGER ---> DATE
		else if (VbBaseType.INTEGER.equals(originalType) && VbBaseType.DATE.equals(typeToConvertTo)) {
			rc.p("toDate(");
			printable.print();
			rc.p(")");
		}
		// LONG ---> BOOLEAN
		else if (VbBaseType.LONG.equals(originalType) && VbBaseType.BOOLEAN.equals(typeToConvertTo)) {
			rc.p("toBool(");
			printable.print();
			rc.p(")");
		}
		// LONG ---> DOUBLE
		else if (VbBaseType.LONG.equals(originalType) && VbBaseType.DOUBLE.equals(typeToConvertTo)) {
			rc.p("(double) ");
			printable.print();
		}
		// LONG ---> INTEGER
		else if (VbBaseType.LONG.equals(originalType) && VbBaseType.INTEGER.equals(typeToConvertTo)) {
			rc.p("toInt(");
			printable.print();
			rc.p(")");
		}
		// LONG ---> STRING
		else if (VbBaseType.LONG.equals(originalType) && VbBaseType.STRING.equals(typeToConvertTo)) {
			rc.p("String.valueOf(");
			printable.print();
			rc.p(")");
		}
		// STRING ---> LONG
		else if (VbBaseType.STRING.equals(originalType) && VbBaseType.LONG.equals(typeToConvertTo)) {
			rc.p("toLong(");
			printable.print();
			rc.p(")");
		}
		// STRING ---> INTEGER
		else if (VbBaseType.STRING.equals(originalType) && VbBaseType.INTEGER.equals(typeToConvertTo)) {
			rc.p("toInt(");
			printable.print();
			rc.p(")");
		}
		// STRING ---> BYTE
		else if (VbBaseType.STRING.equals(originalType) && VbBaseType.BYTE.equals(typeToConvertTo)) {
			rc.p("toByte(");
			printable.print();
			rc.p(")");
		}
		// STRING ---> DATE
		else if (VbBaseType.STRING.equals(originalType) && VbBaseType.DATE.equals(typeToConvertTo)) {
			rc.p("new Date(");
			printable.print();
			rc.p(")");
		}
		// STRING ---> enum
		else if (VbBaseType.STRING.equals(originalType) && typeToConvertTo instanceof Enumeration) {
			rc.p("%s.valueOf(", TypeMappingUtils.mapType(typeToConvertTo));
			printable.print();
			rc.p(")");
		}
		// COLOR ---> LONG
		else if (VbBaseType.COLOR.equals(originalType) && VbBaseType.LONG.equals(typeToConvertTo)) {
			printable.print();
			rc.p(".getLong()");
		}
		// VARIANT ---cast---> !VARIANT
		else if (VbBaseType.VARIANT.equals(originalType) && !VbBaseType.VARIANT.equals(typeToConvertTo)) {
			// variants / objects should be casted to the required type
			rc.p("(%s) ", TypeMappingUtils.mapType(typeToConvertTo));
			printable.print();
		}
		// null ---> BOOLEAN
		else if (originalType == null && VbBaseType.BOOLEAN.equals(typeToConvertTo)) {
			rc.p("toBool(");
			printable.print();
			rc.p(")");
		}
		// null ---> BYTE
		else if (originalType == null && VbBaseType.BYTE.equals(typeToConvertTo)) {
			rc.p("toByte(");
			printable.print();
			rc.p(")");
		}
		// null ---> INTEGER
		else if (originalType == null && VbBaseType.INTEGER.equals(typeToConvertTo)) {
			rc.p("toInt(");
			printable.print();
			rc.p(")");
		}
		// null ---> LONG
		else if (originalType == null && VbBaseType.LONG.equals(typeToConvertTo)) {
			rc.p("toLong(");
			printable.print();
			rc.p(")");
		}
		// null ---> STRING
		else if (originalType == null && VbBaseType.STRING.equals(typeToConvertTo)) {
			rc.p("String.valueOf(");
			printable.print();
			rc.p(")");
		}
		// null --> *
		else if (originalType == null) {
			printable.print();
		}

		// --------------- Fallbacks --------------------

		// !VARIANT ---> VARIANT
		else if (!VbBaseType.VARIANT.equals(originalType) && VbBaseType.VARIANT.equals(typeToConvertTo)) {
			printable.print();
		}
		// !VARIANT ---> null
		else if (!VbBaseType.VARIANT.equals(originalType) && typeToConvertTo == null) {
			printable.print();
		}
		// * ---> *
		else {
			printable.print();
		}
	}
}
