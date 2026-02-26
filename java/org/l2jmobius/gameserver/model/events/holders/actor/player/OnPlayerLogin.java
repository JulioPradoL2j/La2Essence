package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerLogin implements IBaseEvent
{
	private final Player _player;

	public OnPlayerLogin(Player player)
	{
		this._player = player;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_LOGIN;
	}
}
