package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.Mentee;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerMenteeRemove implements IBaseEvent
{
	private final Player _mentor;
	private final Mentee _mentee;

	public OnPlayerMenteeRemove(Player mentor, Mentee mentee)
	{
		this._mentor = mentor;
		this._mentee = mentee;
	}

	public Player getMentor()
	{
		return this._mentor;
	}

	public Mentee getMentee()
	{
		return this._mentee;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_MENTEE_REMOVE;
	}
}
