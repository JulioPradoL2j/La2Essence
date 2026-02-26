package org.l2jmobius.gameserver.network.clientpackets.characterstyle;

import org.l2jmobius.gameserver.data.enums.CharacterStyleCategoryType;
import org.l2jmobius.gameserver.data.holders.CharacterStyleDataHolder;
import org.l2jmobius.gameserver.data.xml.CharacterStylesData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
import org.l2jmobius.gameserver.network.serverpackets.characterstyle.ExCharacterStyleRegister;

public class ExRequestCharacterStyleRegister extends ClientPacket
{
	private int _styleType;
	private int _styleId;

	@Override
	protected void readImpl()
	{
		this._styleType = this.readInt();
		this._styleId = this.readInt();
		this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			CharacterStyleCategoryType category = CharacterStyleCategoryType.getByClientId(this._styleType);
			CharacterStyleDataHolder style = CharacterStylesData.getInstance().getSpecificStyleByCategoryAndId(category, this._styleId);
			if (category != null && style != null)
			{
				for (ItemHolder price : style._cost)
				{
					if (player.getInventory().getInventoryItemCount(price.getId(), -1) < price.getCount())
					{
						player.sendPacket(new SystemMessage(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_REQUIRED_ITEMS));
						player.sendPacket(ExCharacterStyleRegister.STATIC_PACKET_FAIL);
						return;
					}
				}

				for (ItemHolder pricex : style._cost)
				{
					if (player.getInventory().destroyItemByItemId(ItemProcessType.DESTROY, pricex.getId(), pricex.getCount(), player, null) == null)
					{
						player.sendPacket(new SystemMessage(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_REQUIRED_ITEMS));
						player.sendPacket(ExCharacterStyleRegister.STATIC_PACKET_FAIL);
						return;
					}
				}

				player.modifyCharacterStyle(category, this._styleId, false, true);
				player.sendPacket(ExCharacterStyleRegister.STATIC_PACKET_SUCCESS);
			}
			else
			{
				player.sendPacket(ExCharacterStyleRegister.STATIC_PACKET_FAIL);
			}
		}
	}
}
