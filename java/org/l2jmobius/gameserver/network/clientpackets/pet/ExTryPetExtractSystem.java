package org.l2jmobius.gameserver.network.clientpackets.pet;

import org.l2jmobius.gameserver.data.holders.PetExtractionHolder;
import org.l2jmobius.gameserver.data.xml.NpcData;
import org.l2jmobius.gameserver.data.xml.PetDataTable;
import org.l2jmobius.gameserver.data.xml.PetExtractData;
import org.l2jmobius.gameserver.model.PetData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Pet;
import org.l2jmobius.gameserver.model.actor.templates.NpcTemplate;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.model.itemcontainer.PetInventory;
import org.l2jmobius.gameserver.model.itemcontainer.PlayerInventory;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.pet.ResultPetExtractSystem;

public class ExTryPetExtractSystem extends ClientPacket
{
	private int _itemObjId;

	@Override
	protected void readImpl()
	{
		this._itemObjId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			Item petItem = player.getInventory().getItemByObjectId(this._itemObjId);
			if (petItem != null && (player.getPet() == null || player.getPet().getControlItem() != petItem))
			{
				PetData petData = PetDataTable.getInstance().getPetDataByItemId(petItem.getId());
				NpcTemplate npcTemplate = NpcData.getInstance().getTemplate(petData.getNpcId());
				Pet pet = new Pet(npcTemplate, player, petItem);
				PetInventory petInventory = pet.getInventory();
				PlayerInventory playerInventory = player.getInventory();
				if (petInventory == null || playerInventory == null)
				{
					player.sendPacket(new ResultPetExtractSystem(false));
				}
				else if (playerInventory.validateWeight(petInventory.getTotalWeight()) && playerInventory.validateCapacity(petInventory.getSize()))
				{
					petInventory.transferItemsToOwner();
					Pet petInfo = Pet.restore(petItem, NpcData.getInstance().getTemplate(petData.getNpcId()), player);
					int petId = PetDataTable.getInstance().getPetDataByItemId(petItem.getId()).getType();
					int petLevel = petInfo.getLevel();
					PetExtractionHolder holder = PetExtractData.getInstance().getExtraction(petId, petLevel);
					if (holder == null)
					{
						player.sendPacket(new ResultPetExtractSystem(false));
					}
					else
					{
						int extractItemId = holder.getExtractItem();
						int extractItemCount = (int) (petInfo.getStat().getExp() / holder.getExtractExp());
						int extractCostId = holder.getExtractCost().getId();
						long extractCostCount = holder.getExtractCost().getCount() * extractItemCount;
						int defaultCostId = holder.getDefaultCost().getId();
						long defaultCostCount = holder.getDefaultCost().getCount();
						if (player.getInventory().getInventoryItemCount(extractCostId, -1) < extractCostCount || player.getInventory().getInventoryItemCount(defaultCostId, -1) < defaultCostCount)
						{
							player.sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT);
							player.sendPacket(new ResultPetExtractSystem(false));
						}
						else if (player.destroyItemByItemId(ItemProcessType.FEE, extractCostId, extractCostCount, player, true) && player.destroyItemByItemId(ItemProcessType.FEE, defaultCostId, defaultCostCount, player, true) && player.destroyItem(ItemProcessType.FEE, petItem, player, true))
						{
							player.addItem(ItemProcessType.REWARD, extractItemId, extractItemCount, player, true);
							player.sendPacket(new ResultPetExtractSystem(true));
						}
					}
				}
				else
				{
					player.sendPacket(SystemMessageId.CANNOT_BE_SENT_VIA_MAIL_SOLD_AT_A_SHOP_OR_VIA_THE_AUCTION_THE_GUARDIAN_S_INVENTORY_IS_NOT_EMPTY_PLEASE_TAKE_EVERYTHING_FROM_THERE_FIRST);
					player.sendPacket(new ResultPetExtractSystem(false));
				}
			}
			else
			{
				player.sendPacket(new ResultPetExtractSystem(false));
			}
		}
	}
}
