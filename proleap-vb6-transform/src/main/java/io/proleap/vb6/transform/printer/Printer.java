package io.proleap.vb6.transform.printer;

import io.proleap.vb6.asg.metamodel.ScopedElement;

public interface Printer {

	void close();

	void flush();

	void indent();

	void print(String str);

	void print(String format, Object... args);

	void printNewline();

	void printNewline(ScopedElement element);

	void unindent();
}
