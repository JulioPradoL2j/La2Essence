package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExClosePartyRoom extends ServerPacket
{
	public static final ExClosePartyRoom STATIC_PACKET = new ExClosePartyRoom();

	private ExClosePartyRoom()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_DISMISS_PARTY_ROOM.writeId(this, buffer);
	}
}
