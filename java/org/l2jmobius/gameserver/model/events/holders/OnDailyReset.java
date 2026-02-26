package org.l2jmobius.gameserver.model.events.holders;

import org.l2jmobius.gameserver.model.events.EventType;

public class OnDailyReset implements IBaseEvent
{
	@Override
	public EventType getType()
	{
		return EventType.ON_DAILY_RESET;
	}
}
