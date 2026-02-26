package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.ClanEntryManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExPledgeWaitingListApplied;

public class RequestPledgeWaitingApplied extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && player.getClan() == null)
		{
			int clanId = ClanEntryManager.getInstance().getClanIdForPlayerApplication(player.getObjectId());
			if (clanId > 0)
			{
				player.sendPacket(new ExPledgeWaitingListApplied(clanId, player.getObjectId()));
			}
		}
	}
}
