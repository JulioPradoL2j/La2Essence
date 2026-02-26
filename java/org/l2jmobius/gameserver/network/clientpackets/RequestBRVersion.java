package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.network.serverpackets.ExBRVersion;

public class RequestBRVersion extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		this.getClient().sendPacket(new ExBRVersion());
	}
}
