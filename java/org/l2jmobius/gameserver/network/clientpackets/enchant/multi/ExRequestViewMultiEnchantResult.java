package org.l2jmobius.gameserver.network.clientpackets.enchant.multi;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.EnchantItemRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ShortcutInit;
import org.l2jmobius.gameserver.network.serverpackets.enchant.multi.ExResultMultiEnchantItemList;

public class ExRequestViewMultiEnchantResult extends ClientPacket
{
	@Override
	protected void readImpl()
	{
		this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			EnchantItemRequest request = player.getRequest(EnchantItemRequest.class);
			if (request != null)
			{
				player.sendPacket(new ExResultMultiEnchantItemList(player, request.getMultiSuccessEnchantList(), request.getMultiFailureEnchantList(), true));
				player.sendPacket(new ShortcutInit(player));
			}
		}
	}
}
