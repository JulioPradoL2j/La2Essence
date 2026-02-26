package org.l2jmobius.gameserver.network.serverpackets.characterstyle;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCharacterStyleSelect extends ServerPacket
{
	public static final ExCharacterStyleSelect STATIC_PACKET = new ExCharacterStyleSelect();

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHARACTER_STYLE_SELECT.writeId(this, buffer);
		buffer.writeByte(1);
	}
}
