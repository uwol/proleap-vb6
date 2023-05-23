package io.proleap.vb6.analysis.util;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.NamedElement;
import io.proleap.vb6.asg.metamodel.Procedure;
import io.proleap.vb6.asg.metamodel.Program;
import io.proleap.vb6.asg.metamodel.registry.ASGElementRegistry;
import io.proleap.vb6.asg.util.ANTLRUtils;

public class NamingUtils {

	public static String determineFullQualifiedName(final ParseTree ctx, final Program program) {
		final ASGElementRegistry asgElementRegistry = program.getASGElementRegistry();

		final Module module = ANTLRUtils.findParent(Module.class, ctx, asgElementRegistry);
		final Procedure procedure = find(Procedure.class, ctx, asgElementRegistry);

		final List<String> names = new ArrayList<>();

		if (module != null) {
			names.add(getNameString(module));
		}

		if (procedure != null) {
			names.add(getNameString(procedure));
		}

		return String.join(".", names);
	}

	@SuppressWarnings("unchecked")
	protected static <T extends ASGElement> T find(final Class<? extends ASGElement> type, final ParseTree ctx,
			final ASGElementRegistry asgElementRegistry) {
		final ASGElement asgElement = asgElementRegistry.getASGElement(ctx);
		final boolean isInstance = type.isAssignableFrom(asgElement.getClass());
		final T result = isInstance ? (T) asgElement : ANTLRUtils.findParent(type, ctx, asgElementRegistry);
		return result;
	}

	protected static String getNameString(final NamedElement namedElement) {
		return namedElement != null ? namedElement.getName() : "";
	}
}
