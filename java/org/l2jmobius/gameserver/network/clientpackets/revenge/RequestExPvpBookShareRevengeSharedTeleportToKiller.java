package org.l2jmobius.gameserver.network.clientpackets.revenge;

import org.l2jmobius.gameserver.managers.RevengeHistoryManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestExPvpBookShareRevengeSharedTeleportToKiller extends ClientPacket
{
	private String _victimName;
	private String _killerName;

	@Override
	protected void readImpl()
	{
		this._victimName = this.readSizedString();
		this._killerName = this.readSizedString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			RevengeHistoryManager.getInstance().teleportToSharedKiller(player, this._victimName, this._killerName);
		}
	}
}
