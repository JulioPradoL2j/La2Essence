package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExCloseMPCC extends ServerPacket
{
	public static final ExCloseMPCC STATIC_PACKET = new ExCloseMPCC();

	private ExCloseMPCC()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CLOSE_MPCC.writeId(this, buffer);
	}
}
