package org.l2jmobius.gameserver.network.serverpackets.collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCollectionList extends ServerPacket
{
	private final int _category;

	public ExCollectionList(int category)
	{
		this._category = category;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_COLLECTION_LIST.writeId(this, buffer);
		buffer.writeByte(this._category);
		buffer.writeInt(0);
		buffer.writeInt(0);
	}
}
