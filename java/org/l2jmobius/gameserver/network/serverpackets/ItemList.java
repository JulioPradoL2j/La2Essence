package org.l2jmobius.gameserver.network.serverpackets;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ItemList extends AbstractItemPacket
{
	private final int _sendType;
	private final Player _player;
	private final List<Item> _items = new ArrayList<>();

	public ItemList(int sendType, Player player)
	{
		this._sendType = sendType;
		this._player = player;

		for (Item item : player.getInventory().getItems())
		{
			if (!item.isQuestItem())
			{
				this._items.add(item);
			}
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.ITEM_LIST.writeId(this, buffer);
		if (this._sendType == 2)
		{
			buffer.writeByte(this._sendType);
			buffer.writeInt(this._items.size());
			buffer.writeInt(this._items.size());

			for (Item item : this._items)
			{
				this.writeItem(item, buffer);
			}
		}
		else
		{
			buffer.writeByte(1);
			buffer.writeInt(0);
			buffer.writeInt(this._items.size());
		}

		this.writeInventoryBlock(this._player.getInventory(), buffer);
	}
}
