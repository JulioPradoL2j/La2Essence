package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExOpenMPCC extends ServerPacket
{
	public static final ExOpenMPCC STATIC_PACKET = new ExOpenMPCC();

	private ExOpenMPCC()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_OPEN_MPCC.writeId(this, buffer);
	}
}
