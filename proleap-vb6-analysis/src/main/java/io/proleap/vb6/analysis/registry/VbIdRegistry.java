package io.proleap.vb6.analysis.registry;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.registry.ASGElementRegistry;
import io.proleap.vb6.asg.util.ANTLRUtils;

public class VbIdRegistry {

	private static final String SHARP = "#";

	protected final Map<ParserRuleContext, Long> ctxToIdRegistry = new HashMap<ParserRuleContext, Long>();

	protected Long currentId = 0l;

	protected final Map<Long, ParserRuleContext> idToCtxRegistry = new HashMap<Long, ParserRuleContext>();

	public String assureAbsoluteId(final ParserRuleContext ctx, final ASGElementRegistry asgElementRegistry) {
		final Module parentModule = (Module) ANTLRUtils.findParent(Module.class, ctx, asgElementRegistry);
		final String id = assureId(ctx);
		return parentModule.getName() + SHARP + id;
	}

	public String assureId(final ParserRuleContext ctx) {
		Long id = ctxToIdRegistry.get(ctx);

		if (id == null) {
			id = currentId++;

			ctxToIdRegistry.put(ctx, id);
			idToCtxRegistry.put(id, ctx);
		}

		return String.valueOf(id);
	}

	public String assureRelativeId(final ParserRuleContext ctx, final Module module) {
		final ASGElementRegistry asgElementRegistry = module.getProgram().getASGElementRegistry();
		final Module parentModule = (Module) ANTLRUtils.findParent(Module.class, ctx, asgElementRegistry);
		final String id = assureId(ctx);
		final String result;

		if (module == parentModule) {
			result = SHARP + id;
		} else if (parentModule != null) {
			result = parentModule.getName() + SHARP + id;
		} else {
			result = null;
		}

		return result;
	}

	public ParserRuleContext findForId(final Long id) {
		return idToCtxRegistry.get(id);
	}
}
