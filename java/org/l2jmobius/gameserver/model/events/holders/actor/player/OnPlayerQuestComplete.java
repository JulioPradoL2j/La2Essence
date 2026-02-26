package org.l2jmobius.gameserver.model.events.holders.actor.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.holders.IBaseEvent;
import org.l2jmobius.gameserver.model.script.QuestType;

public class OnPlayerQuestComplete implements IBaseEvent
{
	private final Player _player;
	private final int _questId;
	private final QuestType _questType;

	public OnPlayerQuestComplete(Player player, int questId, QuestType questType)
	{
		this._player = player;
		this._questId = questId;
		this._questType = questType;
	}

	public Player getPlayer()
	{
		return this._player;
	}

	public int getQuestId()
	{
		return this._questId;
	}

	public QuestType getQuestType()
	{
		return this._questType;
	}

	@Override
	public EventType getType()
	{
		return EventType.ON_PLAYER_QUEST_COMPLETE;
	}
}
