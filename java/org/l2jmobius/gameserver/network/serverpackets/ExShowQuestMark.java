package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExShowQuestMark extends ServerPacket
{
	private final int _questId;
	private final int _questState;

	public ExShowQuestMark(int questId, int questState)
	{
		this._questId = questId;
		this._questState = questState;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_QUEST_MARK.writeId(this, buffer);
		buffer.writeInt(this._questId);
		buffer.writeInt(this._questState);
	}
}
