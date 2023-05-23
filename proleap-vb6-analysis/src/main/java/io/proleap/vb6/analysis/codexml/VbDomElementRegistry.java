package io.proleap.vb6.analysis.codexml;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.dom4j.Element;

import io.proleap.vb6.analysis.exception.VbAnalysisException;
import io.proleap.vb6.analysis.util.ANTLRUtils;

public class VbDomElementRegistry {

	protected final Map<ParseTree, Element> domElementRegistry = new HashMap<ParseTree, Element>();

	public Element findParentDomElement(final ParseTree ctx) {
		return ANTLRUtils.findParent(Element.class, ctx, domElementRegistry);
	}

	public void put(final ParseTree ctx, final Element element) {
		if (domElementRegistry.containsKey(ctx)) {
			throw new VbAnalysisException("key " + ctx + " already exists in dom element registry");
		}

		domElementRegistry.put(ctx, element);
	}
}
