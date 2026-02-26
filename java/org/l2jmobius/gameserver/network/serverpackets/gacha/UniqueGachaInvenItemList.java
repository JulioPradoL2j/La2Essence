package org.l2jmobius.gameserver.network.serverpackets.gacha;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class UniqueGachaInvenItemList extends ServerPacket
{
	private final int _currPage;
	private final int _maxPages;
	private final List<Item> _items;

	public UniqueGachaInvenItemList(int currPage, int maxPages, List<Item> items)
	{
		this._currPage = currPage;
		this._maxPages = maxPages;
		this._items = items;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_UNIQUE_GACHA_INVEN_ITEM_LIST.writeId(this, buffer);
		buffer.writeByte(this._currPage);
		buffer.writeByte(this._maxPages);
		buffer.writeInt(this._items.size());

		for (Item item : this._items)
		{
			buffer.writeInt(item.getDisplayId());
			buffer.writeLong(item.getCount());
		}
	}
}
