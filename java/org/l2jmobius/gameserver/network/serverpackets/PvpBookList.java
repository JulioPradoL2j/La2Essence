package org.l2jmobius.gameserver.network.serverpackets;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PvpBookList extends ServerPacket
{
	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PVPBOOK_LIST.writeId(this, buffer);
		 
		buffer.writeInt(4);
		buffer.writeInt(5);
		buffer.writeInt(1);

		for (int i = 0; i < 1; i++)
		{
			buffer.writeSizedString("killer" + i);
			buffer.writeSizedString("clanKiller" + i);
			buffer.writeInt(15);
			buffer.writeInt(2);
			buffer.writeInt(10);
			buffer.writeInt((int) LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());
			buffer.writeByte(1);
		}
	}
}
