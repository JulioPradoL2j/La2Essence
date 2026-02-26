package org.l2jmobius.gameserver.network.clientpackets.teleports;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.teleports.ExTeleportFavoritesList;

public class ExRequestTeleportFavoritesUIToggle extends ClientPacket
{
	private boolean _enable;

	@Override
	protected void readImpl()
	{
		this._enable = this.readByte() == 1;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExTeleportFavoritesList(player, this._enable));
		}
	}
}
