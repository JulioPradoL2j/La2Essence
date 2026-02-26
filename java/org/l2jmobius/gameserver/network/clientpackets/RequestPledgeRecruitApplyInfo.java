package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.ClanEntryManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.model.clan.enums.ClanEntryStatus;
import org.l2jmobius.gameserver.network.serverpackets.ExPledgeRecruitApplyInfo;

public class RequestPledgeRecruitApplyInfo extends ClientPacket
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
			Clan clan = player.getClan();
			ClanEntryStatus status;
			if (clan != null && player.isClanLeader() && ClanEntryManager.getInstance().isClanRegistred(player.getClanId()))
			{
				status = ClanEntryStatus.ORDERED;
			}
			else if (clan == null && ClanEntryManager.getInstance().isPlayerRegistred(player.getObjectId()))
			{
				status = ClanEntryStatus.WAITING;
			}
			else
			{
				status = ClanEntryStatus.DEFAULT;
			}

			player.sendPacket(new ExPledgeRecruitApplyInfo(status));
		}
	}
}
