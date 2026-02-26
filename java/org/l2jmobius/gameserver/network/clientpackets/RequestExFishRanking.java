package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.network.PacketLogger;

public class RequestExFishRanking extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		PacketLogger.info("C5: RequestExFishRanking");
	}
}
