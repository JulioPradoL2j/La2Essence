package org.l2jmobius.gameserver.network.serverpackets.quest;

import java.util.LinkedList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.NewQuestData;
import org.l2jmobius.gameserver.managers.ScriptManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.script.Quest;
import org.l2jmobius.gameserver.model.script.QuestState;
import org.l2jmobius.gameserver.model.script.newquestdata.NewQuest;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExQuestAcceptableList extends ServerPacket
{
	private final List<Quest> _availableQuests = new LinkedList<>();

	public ExQuestAcceptableList(Player player)
	{
		ScriptManager scriptManager = ScriptManager.getInstance();

		for (NewQuest newQuest : NewQuestData.getInstance().getQuests())
		{
			Quest quest = scriptManager.getQuest(newQuest.getId());
			if (quest != null && quest.canStartQuest(player))
			{
				QuestState questState = player.getQuestState(quest.getName());
				if (questState == null || !questState.isStarted() && questState.isNowAvailable())
				{
					this._availableQuests.add(quest);
				}
			}
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_QUEST_ACCEPTABLE_LIST.writeId(this, buffer);
		buffer.writeInt(this._availableQuests.size());

		for (Quest quest : this._availableQuests)
		{
			buffer.writeInt(quest.getId());
		}
	}
}
