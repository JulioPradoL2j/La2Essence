package org.l2jmobius.gameserver.network.serverpackets.sayune;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExNotifyFlyMoveStart extends ServerPacket
{
	public static final ExNotifyFlyMoveStart STATIC_PACKET = new ExNotifyFlyMoveStart();

	private ExNotifyFlyMoveStart()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_NOTIFY_FLY_MOVE_START.writeId(this, buffer);
	}
}
