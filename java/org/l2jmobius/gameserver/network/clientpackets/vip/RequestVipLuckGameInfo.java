package org.l2jmobius.gameserver.network.clientpackets.vip;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.vip.ReceiveVipLuckyGameInfo;

public class RequestVipLuckGameInfo extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ReceiveVipLuckyGameInfo(player));
		}
	}
}
