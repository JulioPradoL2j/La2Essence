package org.l2jmobius.gameserver.network.clientpackets.enchant.multi;

import org.l2jmobius.gameserver.data.xml.EnchantItemData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.EnchantItemRequest;
import org.l2jmobius.gameserver.model.item.enchant.EnchantScroll;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.enchant.multi.ExResetSelectMultiEnchantScroll;

public class ExRequestStartMultiEnchantScroll extends ClientPacket
{
	private int _scrollObjectId;

	@Override
	protected void readImpl()
	{
		this._scrollObjectId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (player.getRequest(EnchantItemRequest.class) == null)
			{
				player.addRequest(new EnchantItemRequest(player, this._scrollObjectId));
			}

			EnchantItemRequest request = player.getRequest(EnchantItemRequest.class);
			Item scroll = player.getInventory().getItemByObjectId(this._scrollObjectId);
			EnchantScroll scrollTemplate = EnchantItemData.getInstance().getEnchantScroll(scroll);
			if (scrollTemplate != null && !scrollTemplate.isBlessed() && !scrollTemplate.isBlessedDown() && !scrollTemplate.isSafe() && !scrollTemplate.isGiant())
			{
				request.setEnchantingScroll(this._scrollObjectId);
				player.sendPacket(new ExResetSelectMultiEnchantScroll(player, this._scrollObjectId, 0));
			}
			else
			{
				player.sendPacket(new ExResetSelectMultiEnchantScroll(player, this._scrollObjectId, 1));
			}
		}
	}
}
