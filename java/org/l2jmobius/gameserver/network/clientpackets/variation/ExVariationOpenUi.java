package org.l2jmobius.gameserver.network.clientpackets.variation;

import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExVariationOpenUi extends ClientPacket
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
