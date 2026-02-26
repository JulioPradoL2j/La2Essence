package org.l2jmobius.gameserver.network.serverpackets.newskillenchant;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.AbstractItemPacket;

public class ExSpExtractItem extends AbstractItemPacket
{
	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SP_EXTRACT_ITEM.writeId(this, buffer);
		buffer.writeByte(0);
		buffer.writeByte(0);
		buffer.writeInt(98232);
	}
}
