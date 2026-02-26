package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.script.Quest;
import org.l2jmobius.gameserver.model.script.QuestState;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class GmViewQuestInfo extends ServerPacket
{
	private final Player _player;
	private final Collection<Quest> _questList;

	public GmViewQuestInfo(Player player)
	{
		this._player = player;
		this._questList = player.getAllActiveQuests();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.GM_VIEW_QUEST_INFO.writeId(this, buffer);
		buffer.writeString(this._player.getName());
		buffer.writeShort(this._questList.size());

		for (Quest quest : this._questList)
		{
			QuestState qs = this._player.getQuestState(quest.getName());
			buffer.writeInt(quest.getId());
			buffer.writeInt(qs == null ? 0 : qs.getCond());
		}

		buffer.writeShort(0);
	}
}
