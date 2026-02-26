package org.l2jmobius.gameserver.network.clientpackets.revenge;

import org.l2jmobius.gameserver.managers.RevengeHistoryManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestExPvpBookShareRevengeKillerLocation extends ClientPacket
{
	private String _killerName;

	@Override
	protected void readImpl()
	{
		this.readSizedString();
		this._killerName = this.readSizedString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			RevengeHistoryManager.getInstance().locateKiller(player, this._killerName);
		}
	}
}
