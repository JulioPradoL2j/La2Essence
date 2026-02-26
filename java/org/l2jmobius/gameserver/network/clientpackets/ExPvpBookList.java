package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.network.serverpackets.PvpBookList;

public class ExPvpBookList extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		this.getClient().sendPacket(new PvpBookList());
	}
}
