package org.l2jmobius.gameserver.network.clientpackets.pledgebonus;

import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.pledgebonus.ExPledgeBonusOpen;
import org.l2jmobius.gameserver.network.serverpackets.pledgedonation.ExPledgeDonationInfo;

public class RequestPledgeBonusOpen extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && player.getClan() != null)
		{
			player.sendPacket(new ExPledgeBonusOpen(player));
			long joinedTime = player.getClanJoinExpiryTime() - PlayerConfig.ALT_CLAN_JOIN_MINS * 60000;
			player.sendPacket(new ExPledgeDonationInfo(player.getClanDonationPoints(), joinedTime + 86400000L < System.currentTimeMillis()));
		}
	}
}
