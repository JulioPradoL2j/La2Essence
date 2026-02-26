package org.l2jmobius.gameserver.network.serverpackets.randomcraft;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCraftRandomMake extends ServerPacket
{
	private final int _itemId;
	private final long _itemCount;

	public ExCraftRandomMake(int itemId, long itemCount)
	{
		this._itemId = itemId;
		this._itemCount = itemCount;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CRAFT_RANDOM_MAKE.writeId(this, buffer);
		buffer.writeByte(0);
		buffer.writeShort(15);
		buffer.writeInt(this._itemId);
		buffer.writeLong(this._itemCount);
		buffer.writeByte(0);
	}
}
