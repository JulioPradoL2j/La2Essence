package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerMenteeAdd implements IBaseEvent
{
	private final Player _mentor;
	private final Player _mentee;

	public OnPlayerMenteeAdd(Player mentor, Player mentee)
	{
		this._mentor = mentor;
		this._mentee = mentee;
	}

	public Player getMentor()
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
		return EventType.ON_PLAYER_MENTEE_ADD;
	}
}
