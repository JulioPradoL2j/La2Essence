package org.l2jmobius.gameserver.model.events.holders.actor.creature;

import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.zone.ZoneType;

public class OnCreatureZoneEnter implements IBaseEvent
{
	private final Creature _creature;
	private final ZoneType _zone;

	public OnCreatureZoneEnter(Creature creature, ZoneType zone)
	{
		this._creature = creature;
		this._zone = zone;
	}

	public Creature getCreature()
	{
		return this._creature;
	}

	public ZoneType getZone()
	{
		return this._zone;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_CREATURE_ZONE_ENTER;
	}
}
