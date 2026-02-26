package org.l2jmobius.gameserver.network.serverpackets.compound;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExEnchantTwoOK extends ServerPacket
{
	public static final ExEnchantTwoOK STATIC_PACKET = new ExEnchantTwoOK();

	private ExEnchantTwoOK()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ENCHANT_TWO_OK.writeId(this, buffer);
		buffer.writeInt(0);
	}
}
