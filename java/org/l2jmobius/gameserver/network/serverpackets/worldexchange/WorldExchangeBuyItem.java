package org.l2jmobius.gameserver.network.serverpackets.worldexchange;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class WorldExchangeBuyItem extends ServerPacket
{
	public static final WorldExchangeBuyItem FAIL = new WorldExchangeBuyItem(-1, -1L, (byte) 0);
	private final int _itemObjectId;
	private final long _itemAmount;
	private final byte _type;

	public WorldExchangeBuyItem(int itemObjectId, long itemAmount, byte type)
	{
		this._itemObjectId = itemObjectId;
		this._itemAmount = itemAmount;
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_WORLD_EXCHANGE_BUY_ITEM.writeId(this, buffer);
		buffer.writeInt(this._itemObjectId);
		buffer.writeLong(this._itemAmount);
		buffer.writeByte(this._type);
	}
}
