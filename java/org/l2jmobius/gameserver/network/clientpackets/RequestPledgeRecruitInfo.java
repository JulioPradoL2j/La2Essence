package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.data.sql.ClanTable;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.serverpackets.ExPledgeRecruitInfo;

public class RequestPledgeRecruitInfo extends ClientPacket
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
			Clan clan = ClanTable.getInstance().getClan(this._clanId);
			if (clan != null)
			{
				player.sendPacket(new ExPledgeRecruitInfo(this._clanId));
			}
		}
	}
}
