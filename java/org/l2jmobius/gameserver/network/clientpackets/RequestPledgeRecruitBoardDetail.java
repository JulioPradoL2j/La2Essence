package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.ClanEntryManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.entry.PledgeRecruitInfo;
import org.l2jmobius.gameserver.network.serverpackets.ExPledgeRecruitBoardDetail;

public class RequestPledgeRecruitBoardDetail extends ClientPacket
{
	private int _clanId;

	@Override
	protected void readImpl()
	{
		this._clanId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			PledgeRecruitInfo pledgeRecruitInfo = ClanEntryManager.getInstance().getClanById(this._clanId);
			if (pledgeRecruitInfo != null)
			{
				player.sendPacket(new ExPledgeRecruitBoardDetail(pledgeRecruitInfo));
			}
		}
	}
}
