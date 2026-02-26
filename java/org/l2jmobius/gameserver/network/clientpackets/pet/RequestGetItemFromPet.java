package org.l2jmobius.gameserver.network.clientpackets.pet;

import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.managers.PunishmentManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.instance.Pet;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.pet.PetItemList;

public class RequestGetItemFromPet extends ClientPacket
{
	private int _objectId;
	private long _amount;
	protected int _unknown;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
		this._amount = this.readLong();
		this._unknown = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (this._amount > 0L && player != null && player.hasPet())
		{
			if (!this.getClient().getFloodProtectors().canPerformTransaction())
			{
				player.sendMessage("You get items from pet too fast.");
			}
			else if (!player.hasItemRequest())
			{
				Pet pet = player.getPet();
				Item item = pet.getInventory().getItemByObjectId(this._objectId);
				if (item != null)
				{
					if (this._amount > item.getCount())
					{
						PunishmentManager.handleIllegalPlayerAction(player, this.getClass().getSimpleName() + ": Character " + player.getName() + " of account " + player.getAccountName() + " tried to get item with oid " + this._objectId + " from pet but has invalid count " + this._amount + " item count: " + item.getCount(), GeneralConfig.DEFAULT_PUNISH);
					}
					else
					{
						Item transferedItem = pet.transferItem(ItemProcessType.TRANSFER, this._objectId, this._amount, player.getInventory(), player, pet);
						if (transferedItem != null)
						{
							player.sendPacket(new PetItemList(pet.getInventory().getItems()));
						}
						else
						{
							PacketLogger.warning("Invalid item transfer request: " + pet.getName() + "(pet) --> " + player.getName());
						}
					}
				}
			}
		}
	}
}
