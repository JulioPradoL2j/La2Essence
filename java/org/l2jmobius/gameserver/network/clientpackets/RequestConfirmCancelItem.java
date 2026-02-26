package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.data.xml.VariationData;
import org.l2jmobius.gameserver.managers.PunishmentManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ExPutItemResultForVariationCancel;

public class RequestConfirmCancelItem extends ClientPacket
{
	private int _objectId;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Item item = player.getInventory().getItemByObjectId(this._objectId);
			if (item != null)
			{
				if (item.getOwnerId() != player.getObjectId())
				{
					PunishmentManager.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tryied to destroy augment on item that doesn't own.", GeneralConfig.DEFAULT_PUNISH);
				}
				else if (!item.isAugmented())
				{
					player.sendPacket(SystemMessageId.AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM);
				}
				else if (item.isPvp() && !PlayerConfig.ALT_ALLOW_AUGMENT_PVP_ITEMS)
				{
					player.sendPacket(SystemMessageId.THIS_IS_NOT_A_SUITABLE_ITEM);
				}
				else
				{
					long price = VariationData.getInstance().getCancelFee(item.getId(), item.getAugmentation().getMineralId());
					if (price < 0L)
					{
						player.sendPacket(SystemMessageId.THIS_IS_NOT_A_SUITABLE_ITEM);
					}
					else
					{
						player.sendPacket(new ExPutItemResultForVariationCancel(item, price));
					}
				}
			}
		}
	}
}
