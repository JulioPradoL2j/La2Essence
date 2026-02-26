package org.l2jmobius.gameserver.network.serverpackets.balok;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class BalrogWarGetReward extends ServerPacket
{
	private final boolean _available;

	public BalrogWarGetReward(boolean available)
	{
		this._available = available;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BALROGWAR_GET_REWARD.writeId(this, buffer);
		buffer.writeByte(this._available);
	}
}
