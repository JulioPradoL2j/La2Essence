package org.l2jmobius.gameserver.network.serverpackets.collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCollectionReceiveReward extends ServerPacket
{
	private final int _collectionId;
	private final boolean _success;

	public ExCollectionReceiveReward(int collectionId, boolean success)
	{
		this._collectionId = collectionId;
		this._success = success;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_COLLECTION_RECEIVE_REWARD.writeId(this, buffer);
		buffer.writeShort(this._collectionId);
		buffer.writeByte(this._success);
	}
}
