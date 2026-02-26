package org.l2jmobius.gameserver.network.clientpackets.enchant;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.EnchantItemRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.enchant.EnchantResult;

public class RequestExCancelEnchantItem extends ClientPacket
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
			player.sendPacket(new EnchantResult(2, null, null, 0));
			player.removeRequest(EnchantItemRequest.class);
			player.getChallengeInfo().setChallengePointsPendingRecharge(-1, -1);
		}
	}
}
