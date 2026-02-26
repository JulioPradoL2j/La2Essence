package org.l2jmobius.gameserver.network.serverpackets.collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCollectionComplete extends ServerPacket
{
	private final int _collectionId;

	public ExCollectionComplete(int collectionId)
	{
		this._collectionId = collectionId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_COLLECTION_COMPLETE.writeId(this, buffer);
		buffer.writeShort(this._collectionId);
		buffer.writeInt(0);
	}
}
