package org.l2jmobius.gameserver.network.serverpackets;

import java.util.Map;
import java.util.Map.Entry;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.PremiumItem;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExGetPremiumItemList extends ServerPacket
{
	private final Player _player;
	private final Map<Integer, PremiumItem> _map;

	public ExGetPremiumItemList(Player player)
	{
		this._player = player;
		this._map = this._player.getPremiumItemList();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PREMIUM_ITEM_LIST.writeId(this, buffer);
		buffer.writeInt(this._map.size());

		for (Entry<Integer, PremiumItem> entry : this._map.entrySet())
		{
			PremiumItem item = entry.getValue();
			buffer.writeLong(entry.getKey().intValue());
			buffer.writeInt(item.getItemId());
			buffer.writeLong(item.getCount());
			buffer.writeInt(0);
			buffer.writeString(item.getSender());
		}
	}
}
