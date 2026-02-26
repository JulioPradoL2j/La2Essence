package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExCubeGameCloseUI extends ServerPacket
{
	public static final ExCubeGameCloseUI STATIC_PACKET = new ExCubeGameCloseUI();

	private ExCubeGameCloseUI()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BLOCK_UPSET_LIST.writeId(this, buffer);
		buffer.writeInt(-1);
	}
}
