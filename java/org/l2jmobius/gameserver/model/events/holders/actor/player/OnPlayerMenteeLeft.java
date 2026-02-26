package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.Mentee;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerMenteeLeft implements IBaseEvent
{
	private final Mentee _mentor;
	private final Player _mentee;

	public OnPlayerMenteeLeft(Mentee mentor, Player mentee)
	{
		this._mentor = mentor;
		this._mentee = mentee;
	}

	public Mentee getMentor()
	{
		return this._mentor;
	}

	public Player getMentee()
	{
		return this._mentee;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_MENTEE_LEFT;
	}
}
