package org.l2jmobius.gameserver.network.serverpackets.relics;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRelicsCollectionCompleteAnnounce extends ServerPacket
{
	private final int _relicCollectionId;

	public ExRelicsCollectionCompleteAnnounce(int relicCollectionId)
	{
		this._relicCollectionId = relicCollectionId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RELICS_COLLECTION_COMPLETE_ANNOUNCE.writeId(this, buffer);
		buffer.writeInt(this._relicCollectionId);
	}
}
