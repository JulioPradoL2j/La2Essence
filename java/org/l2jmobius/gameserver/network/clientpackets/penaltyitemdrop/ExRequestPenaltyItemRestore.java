package org.l2jmobius.gameserver.network.clientpackets.penaltyitemdrop;

import org.l2jmobius.gameserver.config.FeatureConfig;
import org.l2jmobius.gameserver.managers.ItemManager;
import org.l2jmobius.gameserver.managers.MailManager;
import org.l2jmobius.gameserver.model.Message;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.holders.ItemPenaltyHolder;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.itemcontainer.Mail;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.enums.MailType;
import org.l2jmobius.gameserver.network.serverpackets.penaltyitemdrop.ExPenaltyItemInfo;
import org.l2jmobius.gameserver.network.serverpackets.penaltyitemdrop.ExPenaltyItemList;
import org.l2jmobius.gameserver.network.serverpackets.penaltyitemdrop.ExPenaltyItemRestore;

public class ExRequestPenaltyItemRestore extends ClientPacket
{
	private int _objectId;
	private boolean _isAdena;

	@Override
	public void readImpl()
	{
		this._objectId = this.readInt();
		this._isAdena = this.readBoolean();
	}

	@Override
	public void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			ItemPenaltyHolder itemPenalty = null;

			for (ItemPenaltyHolder itemPenaltyHolder : player.getItemPenaltyList())
			{
				if (itemPenaltyHolder.getItemObjectId() == this._objectId)
				{
					itemPenalty = itemPenaltyHolder;
					break;
				}
			}

			if (itemPenalty != null)
			{
				int price = this._isAdena ? FeatureConfig.ITEM_PENALTY_RESTORE_ADENA : FeatureConfig.ITEM_PENALTY_RESTORE_LCOIN;
				if (this._isAdena)
				{
					if (!player.reduceAdena(ItemProcessType.RESTORE, price, null, true))
					{
						player.sendPacket(SystemMessageId.NOT_ENOUGH_ADENA);
						return;
					}
				}
				else if (!player.destroyItemByItemId(ItemProcessType.TRANSFER, 91663, price, null, true))
				{
					player.sendPacket(SystemMessageId.NOT_ENOUGH);
					return;
				}

				Item item = player.getItemPenalty().getItemByObjectId(itemPenalty.getItemObjectId());
				player.getItemPenalty().transferItem(ItemProcessType.TRANSFER, item.getObjectId(), item.getCount(), player.getInventory(), player, null);
				player.sendItemList();
				if (itemPenalty.getKillerObjectId() > 0)
				{
					int itemReward = this._isAdena ? 57 : 91663;
					Item rewardItem = ItemManager.createItem(ItemProcessType.REWARD, itemReward, price, null);
					Message msg = new Message(player.getObjectId(), itemPenalty.getKillerObjectId(), "Penalty Item Reward!", "You are reward from Item penalty!", MailType.PRIME_SHOP_GIFT);
					Mail attachments = msg.createAttachments();
					attachments.addItem(ItemProcessType.REWARD, rewardItem, null, null);
					MailManager.getInstance().sendMessage(msg);
				}

				player.removePenaltyItem(itemPenalty);
				player.sendPacket(new ExPenaltyItemRestore());
				player.sendPacket(new ExPenaltyItemList(player));
				player.sendPacket(new ExPenaltyItemInfo(player));
			}
		}
	}
}
