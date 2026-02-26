package org.l2jmobius.gameserver.model.events.holders.actor.npc;

import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnNpcSpawn implements IBaseEvent
{
	private final Npc _npc;

	public OnNpcSpawn(Npc npc)
	{
		this._npc = npc;
	}

	public Npc getNpc()
	{
		return this._npc;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_NPC_SPAWN;
	}
}
