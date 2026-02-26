package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerMentorStatus implements IBaseEvent
{
	private final Player _mentor;
	private final boolean _isOnline;

	public OnPlayerMentorStatus(Player mentor, boolean isOnline)
	{
		this._mentor = mentor;
		this._isOnline = isOnline;
	}

	public Player getMentor()
	{
		return this._mentor;
	}

	public boolean isMentorOnline()
	{
		return this._isOnline;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_MENTOR_STATUS;
	}
}
