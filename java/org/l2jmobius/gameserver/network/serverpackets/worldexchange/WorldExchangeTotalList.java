package org.l2jmobius.gameserver.network.serverpackets.worldexchange;

import java.util.Collection;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class WorldExchangeTotalList extends ServerPacket
{
	private final Collection<Integer> _itemIds;

	public WorldExchangeTotalList(Collection<Integer> itemIds)
	{
		this._itemIds = itemIds;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_WORLD_EXCHANGE_TOTAL_LIST.writeId(this, buffer);
		buffer.writeInt(this._itemIds.size());

		for (int id : this._itemIds)
		{
			buffer.writeInt(id);
			buffer.writeLong(0L);
			buffer.writeLong(0L);
			buffer.writeLong(1L);
		}
	}
}
