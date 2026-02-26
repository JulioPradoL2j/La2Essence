package org.l2jmobius.gameserver.network.serverpackets.gacha;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExUniqueGachaSidebarInfo extends ServerPacket
{
	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_UNIQUE_GACHA_SIDEBAR_INFO.writeId(this, buffer);
		buffer.writeByte(1);
	}
}
