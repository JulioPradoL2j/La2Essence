package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExCubeGameRequestReady extends ServerPacket
{
	public static final ExCubeGameRequestReady STATIC_PACKET = new ExCubeGameRequestReady();

	private ExCubeGameRequestReady()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BLOCK_UPSET_LIST.writeId(this, buffer);
		buffer.writeInt(4);
	}
}
