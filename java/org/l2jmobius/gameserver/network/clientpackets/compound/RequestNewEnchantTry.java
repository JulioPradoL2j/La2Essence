package org.l2jmobius.gameserver.network.clientpackets.compound;

import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.data.xml.CombinationItemsData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.CompoundRequest;
import org.l2jmobius.gameserver.model.item.combination.CombinationItem;
import org.l2jmobius.gameserver.model.item.combination.CombinationItemReward;
import org.l2jmobius.gameserver.model.item.combination.CombinationItemType;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.ExItemAnnounce;
import org.l2jmobius.gameserver.network.serverpackets.InventoryUpdate;
import org.l2jmobius.gameserver.network.serverpackets.compound.ExEnchantFail;
import org.l2jmobius.gameserver.network.serverpackets.compound.ExEnchantOneFail;
import org.l2jmobius.gameserver.network.serverpackets.compound.ExEnchantSucess;
import org.l2jmobius.gameserver.util.Broadcast;

public class RequestNewEnchantTry extends ClientPacket
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
			if (player.isInStoreMode())
			{
				player.sendPacket(SystemMessageId.YOU_CANNOT_DO_THAT_WHILE_IN_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
				player.sendPacket(ExEnchantOneFail.STATIC_PACKET);
			}
			else if (!player.isProcessingTransaction() && !player.isProcessingRequest())
			{
				CompoundRequest request = player.getRequest(CompoundRequest.class);
				if (request != null && !request.isProcessing())
				{
					request.setProcessing(true);
					Item itemOne = request.getItemOne();
					Item itemTwo = request.getItemTwo();
					if (itemOne != null && itemTwo != null)
					{
						if (itemOne.getObjectId() != itemTwo.getObjectId() || itemOne.isStackable() && player.getInventory().getInventoryItemCount(itemOne.getTemplate().getId(), -1) >= 2L)
						{
							CombinationItem combinationItem = CombinationItemsData.getInstance().getItemsBySlots(itemOne.getId(), itemOne.getEnchantLevel(), itemTwo.getId(), itemTwo.getEnchantLevel());
							if (combinationItem == null)
							{
								player.sendPacket(new ExEnchantFail(itemOne.getId(), itemTwo.getId()));
								player.removeRequest(request.getClass());
							}
							else if (combinationItem.getCommission() > player.getAdena())
							{
								player.sendPacket(new ExEnchantFail(itemOne.getId(), itemTwo.getId()));
								player.removeRequest(request.getClass());
								player.sendPacket(SystemMessageId.NOT_ENOUGH_ADENA);
							}
							else
							{
								double random = Rnd.nextDouble() * 100.0;
								boolean success = random <= combinationItem.getChance();
								CombinationItemReward rewardItem = combinationItem.getReward(success ? CombinationItemType.ON_SUCCESS : CombinationItemType.ON_FAILURE);
								int itemId = rewardItem.getId();
								Item item = itemId == 0 ? null : player.addItem(ItemProcessType.REWARD, itemId, rewardItem.getCount(), rewardItem.getEnchantLevel(), null, true);
								if (success)
								{
									player.sendPacket(new ExEnchantSucess(itemId, rewardItem.getEnchantLevel()));
									if (combinationItem.isAnnounce() && item != null)
									{
										Broadcast.toAllOnlinePlayers(new ExItemAnnounce(player, item, 8));
									}
								}
								else
								{
									player.sendPacket(new ExEnchantSucess(itemId, rewardItem.getEnchantLevel()));
								}

								if (player.destroyItem(ItemProcessType.FEE, itemOne, 1L, null, true) && player.destroyItem(ItemProcessType.FEE, itemTwo, 1L, null, true) && (combinationItem.getCommission() <= 0L || player.reduceAdena(ItemProcessType.FEE, combinationItem.getCommission(), player, true)))
								{
									InventoryUpdate iu = new InventoryUpdate();
									if (item != null)
									{
										iu.addModifiedItem(item);
									}

									if (itemOne.isStackable() && itemOne.getCount() > 0L)
									{
										iu.addModifiedItem(itemOne);
									}
									else
									{
										iu.addRemovedItem(itemOne);
									}

									if (itemTwo.isStackable() && itemTwo.getCount() > 0L)
									{
										iu.addModifiedItem(itemTwo);
									}
									else
									{
										iu.addRemovedItem(itemTwo);
									}

									player.sendInventoryUpdate(iu);
								}

								player.removeRequest(request.getClass());
							}
						}
						else
						{
							player.sendPacket(new ExEnchantFail(itemOne.getId(), itemTwo.getId()));
							player.removeRequest(request.getClass());
						}
					}
					else
					{
						player.sendPacket(ExEnchantFail.STATIC_PACKET);
						player.removeRequest(request.getClass());
					}
				}
				else
				{
					player.sendPacket(ExEnchantFail.STATIC_PACKET);
				}
			}
			else
			{
				player.sendPacket(SystemMessageId.YOU_CANNOT_USE_THIS_SYSTEM_DURING_TRADING_PRIVATE_STORE_AND_WORKSHOP_SETUP);
				player.sendPacket(ExEnchantOneFail.STATIC_PACKET);
			}
		}
	}
}
