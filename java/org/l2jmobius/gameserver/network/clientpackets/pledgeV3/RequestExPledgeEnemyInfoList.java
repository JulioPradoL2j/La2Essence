package org.l2jmobius.gameserver.network.clientpackets.pledgeV3;

import org.l2jmobius.gameserver.data.sql.ClanTable;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.clan.Clan;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.pledgeV3.ExPledgeEnemyInfoList;

public class RequestExPledgeEnemyInfoList extends ClientPacket
{
	private int _playerClan;

	@Override
	protected void readImpl()
	{
		this._playerClan = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Clan clan = ClanTable.getInstance().getClan(this._playerClan);
			if (clan != null && clan.getClanMember(player.getObjectId()) != null)
			{
				player.sendPacket(new ExPledgeEnemyInfoList(clan));
			}
		}
	}
}
