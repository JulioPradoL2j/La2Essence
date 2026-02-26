package org.l2jmobius.gameserver.network.serverpackets.attributechange;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExChangeAttributeFail extends ServerPacket
{
	public static final ExChangeAttributeFail STATIC = new ExChangeAttributeFail();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHANGE_ATTRIBUTE_FAIL.writeId(this, buffer);
	}
}
