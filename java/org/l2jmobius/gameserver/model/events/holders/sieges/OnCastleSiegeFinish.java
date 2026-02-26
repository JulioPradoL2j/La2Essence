package org.l2jmobius.gameserver.model.events.holders.sieges;

import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.siege.Siege;

public class OnCastleSiegeFinish implements IBaseEvent
{
	private final Siege _siege;

	public OnCastleSiegeFinish(Siege siege)
	{
		this._siege = siege;
	}

	public Siege getSiege()
	{
		return this._siege;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_CASTLE_SIEGE_FINISH;
	}
}
