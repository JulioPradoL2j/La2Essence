package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.TradeItem;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PrivateStoreManageListSell extends AbstractItemPacket
{
	private final int _sendType;
	private final int _objId;
	private final long _playerAdena;
	private final boolean _packageSale;
	private final Collection<TradeItem> _itemList;
	private final Collection<TradeItem> _sellList;

	public PrivateStoreManageListSell(int sendType, Player player, boolean isPackageSale)
	{
		this._sendType = sendType;
		this._objId = player.getObjectId();
		this._playerAdena = player.getAdena();
		player.getSellList().updateItems();
		this._packageSale = isPackageSale;
		this._itemList = player.getInventory().getAvailableItems(player.getSellList());
		this._sellList = player.getSellList().getItems();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PRIVATE_STORE_MANAGE_LIST.writeId(this, buffer);
		buffer.writeByte(this._sendType);
		if (this._sendType == 2)
		{
			buffer.writeInt(this._itemList.size());
			buffer.writeInt(this._itemList.size());

			for (TradeItem item : this._itemList)
			{
				this.writeItem(item, buffer);
				buffer.writeLong(item.getItem().getReferencePrice() * 2);
			}
		}
		else
		{
			buffer.writeInt(this._objId);
			buffer.writeInt(this._packageSale);
			buffer.writeLong(this._playerAdena);
			buffer.writeInt(0);

			for (TradeItem item : this._itemList)
			{
				this.writeItem(item, buffer);
				buffer.writeLong(item.getItem().getReferencePrice() * 2);
			}

			buffer.writeInt(0);

			for (TradeItem item : this._sellList)
			{
				this.writeItem(item, buffer);
				buffer.writeLong(item.getPrice());
				buffer.writeLong(item.getItem().getReferencePrice() * 2);
			}
		}
	}
}
