package org.l2jmobius.gameserver.network.clientpackets;

import java.util.Arrays;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.data.xml.EnsoulData;
import org.l2jmobius.gameserver.data.xml.ItemData;
import org.l2jmobius.gameserver.managers.PunishmentManager;
import org.l2jmobius.gameserver.model.TradeItem;
import org.l2jmobius.gameserver.model.TradeList;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.enums.creature.AttributeType;
import org.l2jmobius.gameserver.model.actor.enums.player.PrivateStoreType;
import org.l2jmobius.gameserver.model.ensoul.EnsoulOption;
import org.l2jmobius.gameserver.model.item.ItemTemplate;
import org.l2jmobius.gameserver.model.itemcontainer.Inventory;
import org.l2jmobius.gameserver.model.zone.ZoneId;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
import org.l2jmobius.gameserver.network.serverpackets.PrivateStoreManageListBuy;
import org.l2jmobius.gameserver.network.serverpackets.PrivateStoreMsgBuy;
import org.l2jmobius.gameserver.taskmanagers.AttackStanceTaskManager;

public class SetPrivateStoreListBuy extends ClientPacket
{
	private TradeItem[] _items = null;

	@Override
	protected void readImpl()
	{
		int count = this.readInt();
		if (count >= 1 && count <= PlayerConfig.MAX_ITEM_IN_PACKET)
		{
			this._items = new TradeItem[count];

			for (int i = 0; i < count; i++)
			{
				int itemId = this.readInt();
				ItemTemplate template = ItemData.getInstance().getTemplate(itemId);
				if (template == null)
				{
					this._items = null;
					return;
				}

				int enchantLevel = this.readShort();
				this.readShort();
				long cnt = this.readLong();
				long price = this.readLong();
				if (itemId < 1 || cnt < 1L || price < 0L)
				{
					this._items = null;
					return;
				}

				int option1 = this.readInt();
				int option2 = this.readInt();
				int option3 = this.readInt();
				short attackAttributeId = this.readShort();
				int attackAttributeValue = this.readShort();
				int defenceFire = this.readShort();
				int defenceWater = this.readShort();
				int defenceWind = this.readShort();
				int defenceEarth = this.readShort();
				int defenceHoly = this.readShort();
				int defenceDark = this.readShort();
				int visualId = this.readInt();
				EnsoulOption[] soulCrystalOptions = new EnsoulOption[this.readByte()];

				for (int k = 0; k < soulCrystalOptions.length; k++)
				{
					soulCrystalOptions[k] = EnsoulData.getInstance().getOption(this.readInt());
				}

				EnsoulOption[] soulCrystalSpecialOptions = new EnsoulOption[this.readByte()];

				for (int k = 0; k < soulCrystalSpecialOptions.length; k++)
				{
					soulCrystalSpecialOptions[k] = EnsoulData.getInstance().getOption(this.readInt());
				}

				this.readByte();
				this.readByte();
				this.readByte();
				this.readByte();
				this.readByte();
				this.readString();
				TradeItem item = new TradeItem(template, cnt, price);
				item.setEnchant(enchantLevel);
				item.setAugmentation(option1, option2, option3);
				item.setAttackElementType(AttributeType.findByClientId(attackAttributeId));
				item.setAttackElementPower(attackAttributeValue);
				item.setElementDefAttr(AttributeType.FIRE, defenceFire);
				item.setElementDefAttr(AttributeType.WATER, defenceWater);
				item.setElementDefAttr(AttributeType.WIND, defenceWind);
				item.setElementDefAttr(AttributeType.EARTH, defenceEarth);
				item.setElementDefAttr(AttributeType.HOLY, defenceHoly);
				item.setElementDefAttr(AttributeType.DARK, defenceDark);
				item.setVisualId(visualId);
				item.setSoulCrystalOptions(Arrays.asList(soulCrystalOptions));
				item.setSoulCrystalSpecialOptions(Arrays.asList(soulCrystalSpecialOptions));
				this._items[i] = item;
			}
		}
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._items == null)
			{
				player.setPrivateStoreType(PrivateStoreType.NONE);
				player.broadcastUserInfo();
			}
			else if (!player.getAccessLevel().allowTransaction())
			{
				player.sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
			}
			else if (!AttackStanceTaskManager.getInstance().hasAttackStanceTask(player) && !player.isInDuel())
			{
				if (player.isInsideZone(ZoneId.NO_STORE))
				{
					player.sendPacket(new PrivateStoreManageListBuy(1, player));
					player.sendPacket(new PrivateStoreManageListBuy(2, player));
					player.sendPacket(SystemMessageId.YOU_CANNOT_OPEN_A_PRIVATE_STORE_HERE);
					player.sendPacket(ActionFailed.STATIC_PACKET);
				}
				else
				{
					TradeList tradeList = player.getBuyList();
					tradeList.clear();
					if (this._items.length > player.getPrivateBuyStoreLimit())
					{
						player.sendPacket(new PrivateStoreManageListBuy(1, player));
						player.sendPacket(new PrivateStoreManageListBuy(2, player));
						player.sendPacket(SystemMessageId.YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_INPUTTED);
					}
					else
					{
						long totalCost = 0L;

						for (TradeItem i : this._items)
						{
							if (Inventory.MAX_ADENA / i.getCount() < i.getPrice())
							{
								PunishmentManager.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to set price more than " + Inventory.MAX_ADENA + " adena in Private Store - Buy.", GeneralConfig.DEFAULT_PUNISH);
								return;
							}

							tradeList.addItemByItemId(i.getItem().getId(), i.getCount(), i.getPrice());
							totalCost += i.getCount() * i.getPrice();
							if (totalCost > Inventory.MAX_ADENA)
							{
								PunishmentManager.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to set total price more than " + Inventory.MAX_ADENA + " adena in Private Store - Buy.", GeneralConfig.DEFAULT_PUNISH);
								return;
							}
						}

						if (totalCost > player.getAdena())
						{
							player.sendPacket(new PrivateStoreManageListBuy(1, player));
							player.sendPacket(new PrivateStoreManageListBuy(2, player));
							player.sendPacket(SystemMessageId.THE_PURCHASE_PRICE_IS_HIGHER_THAN_THE_AMOUNT_OF_MONEY_YOU_HAVE_YOU_CANNOT_OPEN_A_PRIVATE_STORE);
						}
						else
						{
							player.sitDown();
							player.setPrivateStoreType(PrivateStoreType.BUY);
							player.broadcastUserInfo();
							player.broadcastPacket(new PrivateStoreMsgBuy(player));
						}
					}
				}
			}
			else
			{
				player.sendPacket(SystemMessageId.WHILE_YOU_ARE_ENGAGED_IN_COMBAT_YOU_CANNOT_OPERATE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
				player.sendPacket(new PrivateStoreManageListBuy(1, player));
				player.sendPacket(new PrivateStoreManageListBuy(2, player));
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}
}
