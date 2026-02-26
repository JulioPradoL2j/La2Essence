package org.l2jmobius.gameserver.network.serverpackets.commission;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExShowCommission extends ServerPacket
{
	public static final ExShowCommission STATIC_PACKET = new ExShowCommission();

	private ExShowCommission()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_COMMISSION.writeId(this, buffer);
		buffer.writeInt(1);
	}
}
