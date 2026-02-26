package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.fishing.FishingEndReason;

public class OnPlayerFishing implements IBaseEvent
{
	private final Player _player;
	private final FishingEndReason _reason;

	public OnPlayerFishing(Player player, FishingEndReason reason)
	{
		this._player = player;
		this._reason = reason;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public FishingEndReason getReason()
	{
		return this._reason;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_FISHING;
	}
}
