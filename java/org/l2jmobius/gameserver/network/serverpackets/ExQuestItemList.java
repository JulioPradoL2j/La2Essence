package org.l2jmobius.gameserver.network.serverpackets;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExQuestItemList extends AbstractItemPacket
{
	private final int _sendType;
	private final Player _player;
	private final List<Item> _items = new ArrayList<>();

	public ExQuestItemList(int sendType, Player player)
	{
		this._sendType = sendType;
		this._player = player;

		for (Item item : player.getInventory().getItems())
		{
			if (item.isQuestItem())
			{
				this._items.add(item);
			}
		}
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_QUEST_ITEMLIST.writeId(this, buffer);
		buffer.writeByte(this._sendType);
		if (this._sendType == 2)
		{
			buffer.writeInt(this._items.size());
		}
		else
		{
			buffer.writeShort(0);
		}

		buffer.writeInt(this._items.size());

		for (Item item : this._items)
		{
			this.writeItem(item, buffer);
		}

		this.writeInventoryBlock(this._player.getInventory(), buffer);
	}
}
