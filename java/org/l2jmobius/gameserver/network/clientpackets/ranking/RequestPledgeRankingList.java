package org.l2jmobius.gameserver.network.clientpackets.ranking;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ranking.ExPledgeRankingList;

public class RequestPledgeRankingList extends ClientPacket
{
	private int _category;

	@Override
	protected void readImpl()
	{
		this._category = this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExPledgeRankingList(player, this._category));
		}
	}
}
