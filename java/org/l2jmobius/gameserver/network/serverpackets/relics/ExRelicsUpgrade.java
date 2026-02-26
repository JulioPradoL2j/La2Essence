package org.l2jmobius.gameserver.network.serverpackets.relics;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRelicsUpgrade extends ServerPacket
{
	private final boolean _success;
	private final int _relicId;
	private final int _relicLevel;

	public ExRelicsUpgrade(boolean success, int relicId, int relicLevel)
	{
		this._success = success;
		this._relicId = relicId;
		this._relicLevel = relicLevel;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RELICS_UPGRADE.writeId(this, buffer);
		buffer.writeByte(this._success);
		buffer.writeInt(this._relicId);
		buffer.writeInt(this._relicLevel);
	}
}
