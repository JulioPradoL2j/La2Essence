package org.l2jmobius.gameserver.network.clientpackets.pledgeV3;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.pledgeV3.ExPledgeV3Info;

public class RequestExPledgeV3SetAnnounce extends ClientPacket
{
	private String _announce;
	private boolean _enterWorldShow;

	@Override
	protected void readImpl()
	{
		this._announce = this.readSizedString();
		this._enterWorldShow = this.readByte() == 1;
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
				clan.setNotice(this._announce);
				clan.setNoticeEnabled(this._enterWorldShow);
				clan.broadcastToOnlineMembers(new ExPledgeV3Info(clan.getExp(), clan.getRank(), clan.getNotice(), clan.isNoticeEnabled()));
			}
		}
	}
}
