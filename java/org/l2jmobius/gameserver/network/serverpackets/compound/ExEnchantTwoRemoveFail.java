package org.l2jmobius.gameserver.network.serverpackets.compound;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExEnchantTwoRemoveFail extends ServerPacket
{
	public static final ExEnchantTwoRemoveFail STATIC_PACKET = new ExEnchantTwoRemoveFail();

	private ExEnchantTwoRemoveFail()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ENCHANT_TWO_REMOVE_FAIL.writeId(this, buffer);
	}
}
