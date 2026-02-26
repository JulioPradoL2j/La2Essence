package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExRestartClient extends ServerPacket
{
	public static final ExRestartClient STATIC_PACKET = new ExRestartClient();

	private ExRestartClient()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESTART_CLIENT.writeId(this, buffer);
	}
}
