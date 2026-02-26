package org.l2jmobius.gameserver.network.serverpackets.relics;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRelicsResetAll extends ServerPacket
{
	private final int _relicId;

	public ExRelicsResetAll(int relicId)
	{
		this._relicId = relicId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ALL_RESET_RELICS.writeId(this, buffer);
		buffer.writeInt(this._relicId);
	}
}
