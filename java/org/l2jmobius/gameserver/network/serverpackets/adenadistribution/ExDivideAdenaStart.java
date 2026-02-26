package org.l2jmobius.gameserver.network.serverpackets.adenadistribution;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExDivideAdenaStart extends ServerPacket
{
	public static final ExDivideAdenaStart STATIC_PACKET = new ExDivideAdenaStart();

	private ExDivideAdenaStart()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_DIVIDE_ADENA_START.writeId(this, buffer);
	}
}
