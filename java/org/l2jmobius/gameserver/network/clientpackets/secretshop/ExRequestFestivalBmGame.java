package org.l2jmobius.gameserver.network.clientpackets.secretshop;

import org.l2jmobius.gameserver.managers.events.SecretShopEventManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExRequestFestivalBmGame extends ClientPacket
{
	@Override
	protected void readImpl()
	{
		this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			SecretShopEventManager.getInstance().addTickets(player);
		}
	}
}
