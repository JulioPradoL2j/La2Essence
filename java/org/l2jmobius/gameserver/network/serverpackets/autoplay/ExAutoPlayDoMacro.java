package org.l2jmobius.gameserver.network.serverpackets.autoplay;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExAutoPlayDoMacro extends ServerPacket
{
	public static final ExAutoPlayDoMacro STATIC_PACKET = new ExAutoPlayDoMacro();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_AUTOPLAY_DO_MACRO.writeId(this, buffer);
		buffer.writeInt(276);
	}
}
