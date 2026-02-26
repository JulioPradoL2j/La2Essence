package org.l2jmobius.gameserver.network.clientpackets.enchant;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.EnchantItemRequest;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.enchant.ExRemoveEnchantSupportItemResult;
import org.l2jmobius.gameserver.network.serverpackets.enchant.single.ChangedEnchantTargetItemProbabilityList;

public class RequestExRemoveEnchantSupportItem extends ClientPacket
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
			EnchantItemRequest request = player.getRequest(EnchantItemRequest.class);
			if (request != null && !request.isProcessing())
			{
				Item supportItem = request.getSupportItem();
				if (supportItem == null || supportItem.getCount() >= 0L)
				{
					request.setSupportItem(-1);
				}

				request.setTimestamp(System.currentTimeMillis());
				player.sendPacket(ExRemoveEnchantSupportItemResult.STATIC_PACKET);
				player.sendPacket(new ChangedEnchantTargetItemProbabilityList(player, false));
			}
		}
	}
}
