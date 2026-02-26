package org.l2jmobius.gameserver.network.serverpackets.storereview;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.enums.player.PrivateStoreType;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.clientpackets.storereview.ExRequestPrivateStoreSearchList;
import org.l2jmobius.gameserver.network.serverpackets.AbstractItemPacket;

public class ExPrivateStoreSearchItem extends AbstractItemPacket
{
	private final int _page;
	private final int _maxPage;
	private final int _nSize;
	private final List<ExRequestPrivateStoreSearchList.ShopItem> _items;

	public ExPrivateStoreSearchItem(int page, int maxPage, int nSize, List<ExRequestPrivateStoreSearchList.ShopItem> items)
	{
		this._page = page;
		this._maxPage = maxPage;
		this._nSize = nSize;
		this._items = items;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PRIVATE_STORE_SEARCH_ITEM.writeId(this, buffer);
		buffer.writeByte(this._page);
		buffer.writeByte(this._maxPage);
		buffer.writeInt(this._nSize);
		if (this._nSize > 0)
		{
			for (int itemIndex = (this._page - 1) * 120; itemIndex < this._page * 120 && itemIndex < this._items.size(); itemIndex++)
			{
				ExRequestPrivateStoreSearchList.ShopItem shopItem = this._items.get(itemIndex);
				buffer.writeSizedString(shopItem.getOwner().getName());
				buffer.writeInt(shopItem.getOwner().getObjectId());
				buffer.writeByte(shopItem.getStoreType() == PrivateStoreType.PACKAGE_SELL ? 2 : (shopItem.getStoreType() == PrivateStoreType.SELL ? 0 : 1));
				buffer.writeLong(shopItem.getPrice());
				buffer.writeInt(shopItem.getOwner().getX());
				buffer.writeInt(shopItem.getOwner().getY());
				buffer.writeInt(shopItem.getOwner().getZ());
				buffer.writeInt(this.calculatePacketSize(shopItem.getItemInfo()));
				this.writeItem(shopItem.getItemInfo(), shopItem.getCount(), buffer);
			}
		}
	}
}
