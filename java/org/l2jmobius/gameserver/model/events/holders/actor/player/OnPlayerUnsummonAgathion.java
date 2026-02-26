package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerUnsummonAgathion implements IBaseEvent
{
	private final Player _player;
	private final int _agathionId;

	public OnPlayerUnsummonAgathion(Player player, int agathionId)
	{
		this._player = player;
		this._agathionId = agathionId;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public int getAgathionId()
	{
		return this._agathionId;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_UNSUMMON_AGATHION;
	}
}
