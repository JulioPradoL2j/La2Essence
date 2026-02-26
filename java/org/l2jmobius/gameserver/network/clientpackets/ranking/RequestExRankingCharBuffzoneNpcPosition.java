package org.l2jmobius.gameserver.network.clientpackets.ranking;

import org.l2jmobius.gameserver.managers.RankManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ranking.ExRankingBuffZoneNpcInfo;
import org.l2jmobius.gameserver.network.serverpackets.ranking.ExRankingBuffZoneNpcPosition;

public class RequestExRankingCharBuffzoneNpcPosition extends ClientPacket
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
			int ranker = RankManager.getInstance().getPlayerGlobalRank(player);
			if (ranker == 1)
			{
				player.sendPacket(new ExRankingBuffZoneNpcInfo());
			}

			player.sendPacket(new ExRankingBuffZoneNpcPosition());
		}
	}
}
