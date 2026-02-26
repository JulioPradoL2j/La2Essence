package org.l2jmobius.gameserver.network.clientpackets.newhenna;

import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.data.xml.HennaCombinationData;
import org.l2jmobius.gameserver.data.xml.HennaData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.combination.CombinationItemType;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.henna.CombinationHenna;
import org.l2jmobius.gameserver.model.item.henna.CombinationHennaReward;
import org.l2jmobius.gameserver.model.item.henna.Henna;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.model.itemcontainer.Inventory;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.InventoryUpdate;
import org.l2jmobius.gameserver.network.serverpackets.newhenna.NewHennaPotenCompose;

public class RequestNewHennaCompose extends ClientPacket
{
	private int _slotOneIndex;
	private int _slotOneItemId;
	private int _slotTwoItemId;

	@Override
	protected void readImpl()
	{
		this._slotOneIndex = this.readInt();
		this._slotOneItemId = this.readInt();
		this._slotTwoItemId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Inventory inventory = player.getInventory();
			if (player.getHenna(this._slotOneIndex) != null && (this._slotOneItemId == -1 || inventory.getItemByObjectId(this._slotOneItemId) != null) && (this._slotTwoItemId == -1 || inventory.getItemByObjectId(this._slotTwoItemId) != null))
			{
				Henna henna = player.getHenna(this._slotOneIndex);
				CombinationHenna combinationHennas = HennaCombinationData.getInstance().getByHenna(henna.getDyeId());
				if (combinationHennas == null)
				{
					player.sendPacket(new NewHennaPotenCompose(henna.getDyeId(), -1, false));
				}
				else
				{
					if (this._slotOneItemId != -1 && combinationHennas.getItemOne() != inventory.getItemByObjectId(this._slotOneItemId).getId() || this._slotTwoItemId != -1 && combinationHennas.getItemTwo() != inventory.getItemByObjectId(this._slotTwoItemId).getId())
					{
						PacketLogger.info(this.getClass().getSimpleName() + ": " + player + " has modified client or combination data is outdated!" + System.lineSeparator() + "Henna DyeId: " + henna.getDyeId() + " ItemOne: " + combinationHennas.getItemOne() + " ItemTwo: " + combinationHennas.getItemTwo());
					}

					long commission = combinationHennas.getCommission();
					if (commission <= player.getAdena())
					{
						ItemHolder one = new ItemHolder(combinationHennas.getItemOne(), combinationHennas.getCountOne());
						ItemHolder two = new ItemHolder(combinationHennas.getItemTwo(), combinationHennas.getCountTwo());
						if ((this._slotOneItemId == -1 || inventory.getItemByItemId(one.getId()) != null && inventory.getItemByItemId(one.getId()).getCount() >= one.getCount()) && (this._slotTwoItemId == -1 || inventory.getItemByItemId(two.getId()) != null && inventory.getItemByItemId(two.getId()).getCount() >= two.getCount()))
						{
							InventoryUpdate iu = new InventoryUpdate();
							if (this._slotOneItemId != -1)
							{
								iu.addModifiedItem(inventory.getItemByItemId(one.getId()));
							}

							if (this._slotTwoItemId != -1)
							{
								iu.addModifiedItem(inventory.getItemByItemId(two.getId()));
							}

							iu.addModifiedItem(inventory.getItemByItemId(57));
							if ((this._slotOneItemId == -1 || inventory.destroyItemByItemId(ItemProcessType.FEE, one.getId(), one.getCount(), player, null) != null) && (this._slotTwoItemId == -1 || inventory.destroyItemByItemId(ItemProcessType.FEE, two.getId(), two.getCount(), player, null) != null) && inventory.destroyItemByItemId(ItemProcessType.FEE, 57, commission, player, null) != null)
							{
								if (Rnd.get(0, 100) <= combinationHennas.getChance())
								{
									CombinationHennaReward reward = combinationHennas.getReward(CombinationItemType.ON_SUCCESS);
									player.removeHenna(this._slotOneIndex, false);
									player.addHenna(this._slotOneIndex, HennaData.getInstance().getHenna(reward.getHennaId()));
									player.addItem(ItemProcessType.REWARD, reward.getId(), reward.getCount(), null, false);
									player.sendPacket(new NewHennaPotenCompose(reward.getHennaId(), reward.getId() == 0 ? -1 : reward.getId(), true));
								}
								else
								{
									CombinationHennaReward reward = combinationHennas.getReward(CombinationItemType.ON_FAILURE);
									if (henna.getDyeId() != reward.getHennaId())
									{
										player.removeHenna(this._slotOneIndex, false);
										player.addHenna(this._slotOneIndex, HennaData.getInstance().getHenna(reward.getHennaId()));
									}

									player.addItem(ItemProcessType.REWARD, reward.getId(), reward.getCount(), null, false);
									player.sendPacket(new NewHennaPotenCompose(reward.getHennaId(), reward.getId() == 0 ? -1 : reward.getId(), false));
								}

								player.sendPacket(iu);
							}
							else
							{
								player.sendPacket(new NewHennaPotenCompose(henna.getDyeId(), -1, false));
							}
						}
						else
						{
							player.sendPacket(new NewHennaPotenCompose(henna.getDyeId(), -1, false));
						}
					}
				}
			}
		}
	}
}
