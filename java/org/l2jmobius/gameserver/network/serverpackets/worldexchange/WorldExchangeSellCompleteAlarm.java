package org.l2jmobius.gameserver.network.serverpackets.worldexchange;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class WorldExchangeSellCompleteAlarm extends ServerPacket
{
	private final int _itemId;
	private final long _amount;

	public WorldExchangeSellCompleteAlarm(int itemId, long amount)
	{
		this._itemId = itemId;
		this._amount = amount;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_WORLD_EXCHANGE_SELL_COMPLETE_ALARM.writeId(this, buffer);
		buffer.writeInt(this._itemId);
		buffer.writeLong(this._amount);
	}
}
