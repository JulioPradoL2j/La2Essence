package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;

public class OnPlayerQuestAbort implements IBaseEvent
{
	private final Player _player;
	private final int _questId;

	public OnPlayerQuestAbort(Player player, int questId)
	{
		this._player = player;
		this._questId = questId;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public int getQuestId()
	{
		return this._questId;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_QUEST_ABORT;
	}
}
