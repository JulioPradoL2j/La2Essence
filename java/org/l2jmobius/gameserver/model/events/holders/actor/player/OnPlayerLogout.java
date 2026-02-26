package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerLogout implements IBaseEvent
{
	private final Player _player;

	public OnPlayerLogout(Player player)
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
		return EventType.ON_PLAYER_LOGOUT;
	}
}
