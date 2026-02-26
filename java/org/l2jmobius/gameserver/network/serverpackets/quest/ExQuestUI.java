package org.l2jmobius.gameserver.network.serverpackets.quest;

import java.util.LinkedList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.script.QuestState;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExQuestUI extends ServerPacket
{
	private final List<QuestState> _questStates = new LinkedList<>();
	private int _activeQuestCount = 0;

	public ExQuestUI(Player player)
	{
		for (QuestState questState : player.getAllQuestStates())
		{
			if (questState.getQuest() != null)
			{
				this._questStates.add(questState);
				if (questState.isStarted() && !questState.isCompleted())
				{
					this._activeQuestCount++;
				}
			}
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_QUEST_UI.writeId(this, buffer);
		buffer.writeInt(this._questStates.size());

		for (QuestState questState : this._questStates)
		{
			buffer.writeInt(questState.getQuest().getId());
			buffer.writeInt(questState.getCount());
			buffer.writeByte(questState.getState());
		}

		buffer.writeInt(this._activeQuestCount);
	}
}
