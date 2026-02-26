package org.l2jmobius.gameserver.network.serverpackets.adenadistribution;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExDivideAdenaCancel extends ServerPacket
{
	public static final ExDivideAdenaCancel STATIC_PACKET = new ExDivideAdenaCancel();

	private ExDivideAdenaCancel()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_DIVIDE_ADENA_CANCEL.writeId(this, buffer);
		buffer.writeByte(0);
	}
}
