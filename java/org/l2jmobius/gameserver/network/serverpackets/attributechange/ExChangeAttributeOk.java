package org.l2jmobius.gameserver.network.serverpackets.attributechange;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExChangeAttributeOk extends ServerPacket
{
	public static final ExChangeAttributeOk STATIC = new ExChangeAttributeOk();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHANGE_ATTRIBUTE_OK.writeId(this, buffer);
	}
}
