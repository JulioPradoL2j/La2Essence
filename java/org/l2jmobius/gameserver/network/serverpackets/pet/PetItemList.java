package org.l2jmobius.gameserver.network.serverpackets.pet;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.AbstractItemPacket;

public class PetItemList extends AbstractItemPacket
{
	private final Collection<Item> _items;

	public PetItemList(Collection<Item> items)
	{
		this._items = items;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PET_ITEMLIST.writeId(this, buffer);
		buffer.writeShort(this._items.size());

		for (Item item : this._items)
		{
			this.writeItem(item, buffer);
		}
	}
}
