package org.l2jmobius.gameserver.network.clientpackets.enchant.multi;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.EnchantItemRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExRequestFinishMultiEnchantScroll extends ClientPacket
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
			if (player.getRequest(EnchantItemRequest.class) != null)
			{
				player.removeRequest(EnchantItemRequest.class);
				player.getChallengeInfo().setChallengePointsPendingRecharge(-1, -1);
			}
		}
	}
}
