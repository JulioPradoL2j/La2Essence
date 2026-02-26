package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.TradeItem;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PrivateStoreManageListBuy extends AbstractItemPacket
{
	private final int _sendType;
	private final int _objId;
	private final long _playerAdena;
	private final Collection<Item> _itemList;
	private final Collection<TradeItem> _buyList;

	public PrivateStoreManageListBuy(int sendType, Player player)
	{
		this._sendType = sendType;
		this._objId = player.getObjectId();
		this._playerAdena = player.getAdena();
		this._itemList = player.getInventory().getUniqueItems(false, true, true);
		this._buyList = player.getBuyList().getItems();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PRIVATE_STORE_BUY_MANAGE_LIST.writeId(this, buffer);
		buffer.writeByte(this._sendType);
		if (this._sendType == 2)
		{
			buffer.writeInt(this._itemList.size());
			buffer.writeInt(this._itemList.size());

			for (Item item : this._itemList)
			{
				this.writeItem(item, buffer);
				buffer.writeLong(item.getTemplate().getReferencePrice() * 2);
			}
		}
		else
		{
			buffer.writeInt(this._objId);
			buffer.writeLong(this._playerAdena);
			buffer.writeInt(0);

			for (Item item : this._itemList)
			{
				this.writeItem(item, buffer);
				buffer.writeLong(item.getTemplate().getReferencePrice() * 2);
			}

			buffer.writeInt(0);

			for (TradeItem item : this._buyList)
			{
				this.writeItem(item, buffer);
				buffer.writeLong(item.getPrice());
				buffer.writeLong(item.getItem().getReferencePrice() * 2);
				buffer.writeLong(item.getCount());
			}
		}
	}
}
