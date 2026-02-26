package org.l2jmobius.gameserver.network.serverpackets.compound;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExEnchantOneRemoveFail extends ServerPacket
{
	public static final ExEnchantOneRemoveFail STATIC_PACKET = new ExEnchantOneRemoveFail();

	private ExEnchantOneRemoveFail()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ENCHANT_ONE_REMOVE_FAIL.writeId(this, buffer);
	}
}
