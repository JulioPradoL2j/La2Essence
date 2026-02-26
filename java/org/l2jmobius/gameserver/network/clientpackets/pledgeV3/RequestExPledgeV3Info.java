package org.l2jmobius.gameserver.network.clientpackets.pledgeV3;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.PledgeReceiveWarList;
import org.l2jmobius.gameserver.network.serverpackets.pledgeV3.ExPledgeClassicRaidInfo;
import org.l2jmobius.gameserver.network.serverpackets.pledgeV3.ExPledgeV3Info;

public class RequestExPledgeV3Info extends ClientPacket
{
	private int _page;

	@Override
	protected void readImpl()
	{
		this._page = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Clan clan = player.getClan();
			if (clan != null)
			{
				player.sendPacket(new ExPledgeV3Info(clan.getExp(), clan.getRank(), clan.getNotice(), clan.isNoticeEnabled()));
				player.sendPacket(new PledgeReceiveWarList(clan, this._page));
				player.sendPacket(new ExPledgeClassicRaidInfo(player));
			}
		}
	}
}
