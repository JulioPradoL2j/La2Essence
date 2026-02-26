package org.l2jmobius.gameserver.network.clientpackets.enchant;

import org.l2jmobius.gameserver.data.xml.EnchantItemData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.EnchantItemRequest;
import org.l2jmobius.gameserver.model.item.enchant.EnchantScroll;
import org.l2jmobius.gameserver.model.item.enchant.EnchantSupportItem;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.enchant.ExPutEnchantSupportItemResult;
import org.l2jmobius.gameserver.network.serverpackets.enchant.single.ChangedEnchantTargetItemProbabilityList;

public class RequestExTryToPutEnchantSupportItem extends ClientPacket
{
	private int _supportObjectId;
	private int _enchantObjectId;

	@Override
	protected void readImpl()
	{
		this._supportObjectId = this.readInt();
		this._enchantObjectId = this.readInt();
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
				request.setEnchantingItem(this._enchantObjectId);
				request.setSupportItem(this._supportObjectId);
				Item item = request.getEnchantingItem();
				Item scroll = request.getEnchantingScroll();
				Item support = request.getSupportItem();
				if (item != null && scroll != null && support != null)
				{
					EnchantScroll scrollTemplate = EnchantItemData.getInstance().getEnchantScroll(scroll);
					EnchantSupportItem supportTemplate = EnchantItemData.getInstance().getSupportItem(support);
					if (scrollTemplate != null && supportTemplate != null && scrollTemplate.isValid(item, supportTemplate))
					{
						request.setSupportItem(support.getObjectId());
						request.setTimestamp(System.currentTimeMillis());
						player.sendPacket(new ExPutEnchantSupportItemResult(this._supportObjectId));
						player.sendPacket(new ChangedEnchantTargetItemProbabilityList(player, false));
					}
					else
					{
						player.sendPacket(SystemMessageId.AUGMENTATION_REQUIREMENTS_ARE_NOT_FULFILLED);
						request.setSupportItem(-1);
						player.sendPacket(new ExPutEnchantSupportItemResult(0));
					}
				}
				else
				{
					player.sendPacket(SystemMessageId.AUGMENTATION_REQUIREMENTS_ARE_NOT_FULFILLED);
					request.setEnchantingItem(-1);
					request.setSupportItem(-1);
				}
			}
		}
	}
}
