package org.l2jmobius.gameserver.model.events.holders.actor.npc;

import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnNpcCanBeSeen implements IBaseEvent
{
	private final Npc _npc;
	private final Player _player;

	public OnNpcCanBeSeen(Npc npc, Player player)
	{
		this._npc = npc;
		this._player = player;
	}

	public Npc getNpc()
	{
		return this._npc;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_NPC_CAN_BE_SEEN;
	}
}
