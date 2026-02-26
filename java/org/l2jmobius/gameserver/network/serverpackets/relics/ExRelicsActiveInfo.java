package org.l2jmobius.gameserver.network.serverpackets.relics;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRelicsActiveInfo extends ServerPacket
{
	private final int _relicId;
	private final int _relicLevel;

	public ExRelicsActiveInfo(int relicId, int relicLevel)
	{
		this._relicId = relicId;
		this._relicLevel = relicLevel;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RELICS_ACTIVE_INFO.writeId(this, buffer);
		buffer.writeInt(this._relicId);
		buffer.writeInt(this._relicLevel);
	}
}
