package io.proleap.vb6.transform.java.rules.lang.statement.event;

import javax.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.EventStmtContext;
import io.proleap.vb6.asg.metamodel.statement.event.Event;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class EventRule extends VbTransformRule<EventStmtContext, Event> {

	@Override
	public void apply(final EventStmtContext ctx, final Event event, final RuleContext rc) {
	}

	@Override
	public Class<EventStmtContext> from() {
		return EventStmtContext.class;
	}
}
