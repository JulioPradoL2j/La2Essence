package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPrivateStoreBuyingResult extends ServerPacket
{
	private final int _objectId;
	private final long _count;
	private final String _seller;

	public ExPrivateStoreBuyingResult(int objectId, long count, String seller)
	{
		this._objectId = objectId;
		this._count = count;
		this._seller = seller;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PRIVATE_STORE_BUYING_RESULT.writeId(this, buffer);
		buffer.writeInt(this._objectId);
		buffer.writeLong(this._count);
		buffer.writeString(this._seller);
	}
}
