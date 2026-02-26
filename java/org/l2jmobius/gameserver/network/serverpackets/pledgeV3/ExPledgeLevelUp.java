package org.l2jmobius.gameserver.network.serverpackets.pledgeV3;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPledgeLevelUp extends ServerPacket
{
	private final int _level;

	public ExPledgeLevelUp(int level)
	{
		this._level = level;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_LEVEL_UP.writeId(this, buffer);
		buffer.writeInt(this._level);
	}
}
