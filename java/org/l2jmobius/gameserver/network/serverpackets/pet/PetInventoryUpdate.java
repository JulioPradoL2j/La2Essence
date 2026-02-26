package org.l2jmobius.gameserver.network.serverpackets.pet;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.ItemInfo;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.AbstractInventoryUpdate;

public class PetInventoryUpdate extends AbstractInventoryUpdate
{
	public PetInventoryUpdate()
	{
	}

	public PetInventoryUpdate(Item item)
	{
		super(item);
	}

	public PetInventoryUpdate(List<ItemInfo> items)
	{
		super(items);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PET_INVENTORY_UPDATE.writeId(this, buffer);
		this.writeItems(buffer);
	}
}
