package org.l2jmobius.gameserver.network.serverpackets;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.ItemInfo;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class InventoryUpdate extends AbstractInventoryUpdate
{
	public InventoryUpdate()
	{
	}

	public InventoryUpdate(Item item)
	{
		super(item);
	}

	public InventoryUpdate(List<ItemInfo> items)
	{
		super(items);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.INVENTORY_UPDATE.writeId(this, buffer);
		this.writeItems(buffer);
	}
}
