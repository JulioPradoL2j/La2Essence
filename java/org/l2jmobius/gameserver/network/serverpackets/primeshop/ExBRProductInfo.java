package org.l2jmobius.gameserver.network.serverpackets.primeshop;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.primeshop.PrimeShopGroup;
import org.l2jmobius.gameserver.model.primeshop.PrimeShopItem;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExBRProductInfo extends ServerPacket
{
	private final PrimeShopGroup _item;
	private final int _charPoints;
	private final long _charAdena;
	private final long _charCoins;

	public ExBRProductInfo(PrimeShopGroup item, Player player)
	{
		this._item = item;
		this._charPoints = player.getPrimePoints();
		this._charAdena = player.getAdena();
		this._charCoins = player.getInventory().getInventoryItemCount(23805, -1);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BR_PRODUCT_INFO_ACK.writeId(this, buffer);
		buffer.writeInt(this._item.getBrId());
		buffer.writeInt(this._item.getPrice());
		buffer.writeInt(this._item.getItems().size());

		for (PrimeShopItem item : this._item.getItems())
		{
			buffer.writeInt(item.getId());
			buffer.writeInt((int) item.getCount());
			buffer.writeInt(item.getWeight());
			buffer.writeInt(item.isTradable());
		}

		buffer.writeLong(this._charAdena);
		buffer.writeLong(this._charPoints);
		buffer.writeLong(this._charCoins);
	}
}
