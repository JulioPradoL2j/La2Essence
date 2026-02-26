package org.l2jmobius.gameserver.network.serverpackets.collection;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.holders.CollectionDataHolder;
import org.l2jmobius.gameserver.data.xml.CollectionData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCollectionSummary extends ServerPacket
{
	private final Collection<CollectionDataHolder> _collections = CollectionData.getInstance().getCollections();

	public ExCollectionSummary(Player player)
	{
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_COLLECTION_SUMMARY.writeId(this, buffer);
		buffer.writeInt(this._collections.size());

		for (CollectionDataHolder collection : this._collections)
		{
			buffer.writeShort(collection.getCollectionId());
			buffer.writeInt(0);
		}
	}
}
