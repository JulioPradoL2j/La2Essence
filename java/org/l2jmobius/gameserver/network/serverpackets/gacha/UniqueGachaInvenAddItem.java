package org.l2jmobius.gameserver.network.serverpackets.gacha;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.item.holders.GachaItemHolder;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class UniqueGachaInvenAddItem extends ServerPacket
{
	private final List<GachaItemHolder> _rewards;

	public UniqueGachaInvenAddItem(List<GachaItemHolder> rewards)
	{
		this._rewards = rewards;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_UNIQUE_GACHA_INVEN_ADD_ITEM.writeId(this, buffer);
		buffer.writeInt(this._rewards.size());

		for (ItemHolder item : this._rewards)
		{
			buffer.writeInt(item.getId());
			buffer.writeLong(item.getCount());
		}
	}
}
