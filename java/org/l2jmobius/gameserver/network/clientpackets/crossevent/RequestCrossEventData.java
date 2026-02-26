package org.l2jmobius.gameserver.network.clientpackets.crossevent;

import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestCrossEventData extends ClientPacket
{
	@Override
	protected void readImpl()
	{
		this.readByte();
	}

	@Override
	protected void runImpl()
	{
	}
}
