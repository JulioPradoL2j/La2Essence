package org.l2jmobius.gameserver.network.clientpackets.teleports;

import org.l2jmobius.gameserver.data.holders.SharedTeleportHolder;
import org.l2jmobius.gameserver.managers.SharedTeleportManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.teleports.ExShowSharedLocationTeleportUi;

public class ExRequestSharedLocationTeleportUi extends ClientPacket
{
	private int _id;

	@Override
	protected void readImpl()
	{
		this._id = (this.readInt() - 1) / 256;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			SharedTeleportHolder teleport = SharedTeleportManager.getInstance().getTeleport(this._id);
			if (teleport != null)
			{
				player.sendPacket(new ExShowSharedLocationTeleportUi(teleport));
			}
		}
	}
}
