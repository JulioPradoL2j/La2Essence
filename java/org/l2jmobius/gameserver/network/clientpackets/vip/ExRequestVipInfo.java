package org.l2jmobius.gameserver.network.clientpackets.vip;

import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.vip.ReceiveVipInfo;

public class ExRequestVipInfo extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		this.getClient().sendPacket(new ReceiveVipInfo(this.getPlayer()));
	}
}
