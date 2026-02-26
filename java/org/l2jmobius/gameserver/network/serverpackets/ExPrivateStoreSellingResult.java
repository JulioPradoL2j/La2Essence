package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPrivateStoreSellingResult extends ServerPacket
{
	private final int _objectId;
	private final long _count;
	private final String _buyer;

	public ExPrivateStoreSellingResult(int objectId, long count, String buyer)
	{
		this._objectId = objectId;
		this._count = count;
		this._buyer = buyer;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PRIVATE_STORE_SELLING_RESULT.writeId(this, buffer);
		buffer.writeInt(this._objectId);
		buffer.writeLong(this._count);
		buffer.writeString(this._buyer);
	}
}
