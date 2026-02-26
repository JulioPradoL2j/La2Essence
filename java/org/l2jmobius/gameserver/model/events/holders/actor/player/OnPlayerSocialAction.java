package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerSocialAction implements IBaseEvent
{
	private final Player _player;
	private final int _socialActionId;

	public OnPlayerSocialAction(Player player, int socialActionId)
	{
		this._player = player;
		this._socialActionId = socialActionId;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public int getSocialActionId()
	{
		return this._socialActionId;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_SOCIAL_ACTION;
	}
}
