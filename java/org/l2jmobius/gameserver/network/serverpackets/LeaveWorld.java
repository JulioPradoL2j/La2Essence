package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class LeaveWorld extends ServerPacket
{
	public static final LeaveWorld STATIC_PACKET = new LeaveWorld();

	private LeaveWorld()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.LOGOUT_OK.writeId(this, buffer);
	}
}
