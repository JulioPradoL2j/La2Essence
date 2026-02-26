package org.l2jmobius.gameserver.network.serverpackets.collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCollectionUpdateFavorite extends ServerPacket
{
	private final int _isAdd;
	private final int _collectionId;

	public ExCollectionUpdateFavorite(int isAdd, int collectionId)
	{
		this._isAdd = isAdd;
		this._collectionId = collectionId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_COLLECTION_UPDATE_FAVORITE.writeId(this, buffer);
		buffer.writeByte(this._isAdd);
		buffer.writeShort(this._collectionId);
	}
}
