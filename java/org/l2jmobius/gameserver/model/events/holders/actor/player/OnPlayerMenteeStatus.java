package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerMenteeStatus implements IBaseEvent
{
	private final Player _mentee;
	private final boolean _isOnline;

	public OnPlayerMenteeStatus(Player mentee, boolean isOnline)
	{
		this._mentee = mentee;
		this._isOnline = isOnline;
	}

	public Player getMentee()
	{
		return this._mentee;
	}

	public boolean isMenteeOnline()
	{
		return this._isOnline;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_MENTEE_STATUS;
	}
}
