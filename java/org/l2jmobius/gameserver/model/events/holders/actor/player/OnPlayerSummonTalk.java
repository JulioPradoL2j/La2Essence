package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Summon;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerSummonTalk implements IBaseEvent
{
	private final Summon _summon;

	public OnPlayerSummonTalk(Summon summon)
	{
		this._summon = summon;
	}

	public Summon getSummon()
	{
		return this._summon;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_SUMMON_TALK;
	}
}
