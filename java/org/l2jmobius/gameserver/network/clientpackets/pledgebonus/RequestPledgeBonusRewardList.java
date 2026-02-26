package org.l2jmobius.gameserver.network.clientpackets.pledgebonus;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.pledgebonus.ExPledgeBonusList;

public class RequestPledgeBonusRewardList extends ClientPacket
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
			player.sendPacket(new ExPledgeBonusList());
		}
	}
}
