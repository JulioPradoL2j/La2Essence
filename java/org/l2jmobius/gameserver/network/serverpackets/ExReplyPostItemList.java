package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExReplyPostItemList extends AbstractItemPacket
{
	private final int _sendType;
	private final Player _player;
	private final Collection<Item> _itemList;

	public ExReplyPostItemList(int sendType, Player player)
	{
		this._sendType = sendType;
		this._player = player;
		this._itemList = this._player.getInventory().getAvailableItems(true, false, false);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_REPLY_POST_ITEM_LIST.writeId(this, buffer);
		buffer.writeByte(this._sendType);
		buffer.writeInt(this._itemList.size());
		if (this._sendType == 2)
		{
			buffer.writeInt(this._itemList.size());

			for (Item item : this._itemList)
			{
				this.writeItem(item, buffer);
			}
		}
	}
}
