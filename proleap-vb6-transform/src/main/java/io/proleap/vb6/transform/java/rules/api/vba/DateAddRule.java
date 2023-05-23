package io.proleap.vb6.transform.java.rules.api.vba;

import java.util.List;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ICS_S_MembersCallContext;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.MembersCall;
import io.proleap.vb6.asg.metamodel.call.impl.MembersCallImpl;
import io.proleap.vb6.asg.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class DateAddRule extends VbTransformRule<ICS_S_MembersCallContext, MembersCall> {

	@Override
	public void apply(final ICS_S_MembersCallContext ctx, final MembersCall membersCall, final RuleContext rc) {
		final List<Call> subCalls = membersCall.getSubCalls();

		rc.p("Functions");

		for (int i = 1; i < subCalls.size(); i++) {
			final Call subCall = subCalls.get(i);
			rc.visit(subCall.getCtx());
		}

		rc.p(";");
		rc.pNl();
	}

	@Override
	public Class<ICS_S_MembersCallContext> from() {
		return ICS_S_MembersCallContext.class;
	}

	@Override
	public Integer getPriority() {
		return 256;
	}

	@Override
	public boolean where(final ICS_S_MembersCallContext ctx, final MembersCall membersCall, final RuleContext rc) {
		final MembersCall pattern = new MembersCallImpl(membersCall.getModule(), null, null);
		pattern.addSubCall(new UndefinedCallImpl("Vba", null, membersCall.getModule(), null, null));
		pattern.addSubCall(new UndefinedCallImpl("DateAdd", null, membersCall.getModule(), null, null));

		final boolean result = pattern.equals(membersCall);
		return result;
	}
}
