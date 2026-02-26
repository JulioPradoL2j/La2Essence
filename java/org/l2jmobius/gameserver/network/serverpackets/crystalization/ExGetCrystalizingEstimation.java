package org.l2jmobius.gameserver.network.serverpackets.crystalization;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.item.holders.ItemChanceHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExGetCrystalizingEstimation extends ServerPacket
{
	private final List<ItemChanceHolder> _items;

	public ExGetCrystalizingEstimation(List<ItemChanceHolder> items)
	{
		this._items = items;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESPONSE_CRYSTALITEM_INFO.writeId(this, buffer);
		buffer.writeInt(this._items.size());

		for (ItemChanceHolder holder : this._items)
		{
			buffer.writeInt(holder.getId());
			buffer.writeLong(holder.getCount());
			buffer.writeInt((int) (holder.getChance() * 1000.0));
		}
	}
}
