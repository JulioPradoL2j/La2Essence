package org.l2jmobius.gameserver.network.serverpackets.commission;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCloseCommission extends ServerPacket
{
	public static final ExCloseCommission STATIC_PACKET = new ExCloseCommission();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CLOSE_COMMISSION.writeId(this, buffer);
	}
}
