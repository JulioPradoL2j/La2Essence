package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.model.buylist.Product;
import org.l2jmobius.gameserver.model.buylist.ProductList;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ShopPreviewList extends ServerPacket
{
	private final int _listId;
	private final Collection<Product> _list;
	private final long _money;

	public ShopPreviewList(ProductList list, long currentMoney)
	{
		this._listId = list.getListId();
		this._list = list.getProducts();
		this._money = currentMoney;
	}

	public ShopPreviewList(Collection<Product> lst, int listId, long currentMoney)
	{
		this._listId = listId;
		this._list = lst;
		this._money = currentMoney;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.BUY_PREVIEW_LIST.writeId(this, buffer);
		buffer.writeInt(5056);
		buffer.writeLong(this._money);
		buffer.writeInt(this._listId);
		int newlength = 0;

		for (Product product : this._list)
		{
			if (product.getItem().isEquipable())
			{
				newlength++;
			}
		}

		buffer.writeShort(newlength);

		for (Product productx : this._list)
		{
			if (productx.getItem().isEquipable())
			{
				buffer.writeInt(productx.getItemId());
				buffer.writeShort(productx.getItem().getType2());
				if (productx.getItem().getType1() != 4)
				{
					buffer.writeLong(productx.getItem().getBodyPart().getMask());
				}
				else
				{
					buffer.writeLong(0L);
				}

				buffer.writeLong(GeneralConfig.WEAR_PRICE);
			}
		}
	}
}
