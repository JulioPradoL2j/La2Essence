package org.l2jmobius.gameserver.network.clientpackets.teleports;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.teleports.ExTeleportFavoritesList;

public class ExRequestTeleportFavoriteList extends ClientPacket
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
			player.sendPacket(new ExTeleportFavoritesList(player, true));
		}
	}
}
