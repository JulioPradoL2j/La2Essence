package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.TradeItem;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class PrivateStoreListBuy extends AbstractItemPacket
{
	private final int _objId;
	private final long _playerAdena;
	private final Collection<TradeItem> _items;

	public PrivateStoreListBuy(Player player, Player storePlayer)
	{
		this._objId = storePlayer.getObjectId();
		this._playerAdena = player.getAdena();
		storePlayer.getSellList().updateItems();
		this._items = storePlayer.getBuyList().getAvailableItems(player.getInventory());
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.PRIVATE_STORE_BUY_LIST.writeId(this, buffer);
		buffer.writeInt(this._objId);
		buffer.writeLong(this._playerAdena);
		buffer.writeInt(0);
		buffer.writeInt(this._items.size());
		int slotNumber = 0;

		for (TradeItem item : this._items)
		{
			slotNumber++;
			this.writeItem(item, buffer);
			buffer.writeInt(slotNumber);
			buffer.writeLong(item.getPrice());
			buffer.writeLong(item.getItem().getReferencePrice() * 2);
			buffer.writeLong(item.getStoreCount());
		}
	}
}
