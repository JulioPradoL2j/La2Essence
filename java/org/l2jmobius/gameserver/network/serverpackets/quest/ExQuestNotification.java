package org.l2jmobius.gameserver.network.serverpackets.quest;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.script.QuestState;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExQuestNotification extends ServerPacket
{
	private final QuestState _questState;

	public ExQuestNotification(QuestState questState)
	{
		this._questState = questState;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_QUEST_NOTIFICATION.writeId(this, buffer);
		buffer.writeInt(this._questState.getQuest().getId());
		buffer.writeInt(this._questState.getCount());
		buffer.writeByte(this._questState.getState());
	}
}
