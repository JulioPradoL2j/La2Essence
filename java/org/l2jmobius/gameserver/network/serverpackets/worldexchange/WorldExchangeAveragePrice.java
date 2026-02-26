package org.l2jmobius.gameserver.network.serverpackets.worldexchange;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.WorldExchangeManager;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class WorldExchangeAveragePrice extends ServerPacket
{
	private final int _itemId;
	private final long _averagePrice;

	public WorldExchangeAveragePrice(int itemId)
	{
		this._itemId = itemId;
		this._averagePrice = WorldExchangeManager.getInstance().getAveragePriceOfItem(itemId);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_WORLD_EXCHANGE_AVERAGE_PRICE.writeId(this, buffer);
		buffer.writeInt(this._itemId);
		buffer.writeLong(this._averagePrice);
	}
}
