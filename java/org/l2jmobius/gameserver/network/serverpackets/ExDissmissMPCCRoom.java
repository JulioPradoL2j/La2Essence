package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExDissmissMPCCRoom extends ServerPacket
{
	public static final ExDissmissMPCCRoom STATIC_PACKET = new ExDissmissMPCCRoom();

	private ExDissmissMPCCRoom()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_DISMISS_MPCC_ROOM.writeId(this, buffer);
	}
}
