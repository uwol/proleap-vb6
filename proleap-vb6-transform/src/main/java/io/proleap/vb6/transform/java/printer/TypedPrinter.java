package io.proleap.vb6.transform.java.printer;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.asg.metamodel.type.Type;

public interface TypedPrinter {

	void printWithAdjustedType(ParseTree ctx, String suffix, Type originalType, Type typeToConvertTo);

	void printWithAdjustedType(ParseTree ctx, Type originalType, Type typeToConvertTo);

}
