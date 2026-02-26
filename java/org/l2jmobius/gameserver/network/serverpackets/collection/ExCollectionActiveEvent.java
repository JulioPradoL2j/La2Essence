package org.l2jmobius.gameserver.network.serverpackets.collection;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.holders.CollectionDataHolder;
import org.l2jmobius.gameserver.data.xml.CollectionData;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCollectionActiveEvent extends ServerPacket
{
	private final List<CollectionDataHolder> _collections = CollectionData.getInstance().getCollectionsByTabId(7);

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_COLLECTION_ACTIVE_EVENT.writeId(this, buffer);
		buffer.writeInt(this._collections.size());

		for (CollectionDataHolder collection : this._collections)
		{
			buffer.writeShort(collection.getCollectionId());
		}
	}
}
