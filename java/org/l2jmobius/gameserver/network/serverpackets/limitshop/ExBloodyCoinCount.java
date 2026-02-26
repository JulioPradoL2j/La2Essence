package org.l2jmobius.gameserver.network.serverpackets.limitshop;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExBloodyCoinCount extends ServerPacket
{
	private final long _count;

	public ExBloodyCoinCount(Player player)
	{
		this._count = player.getInventory().getInventoryItemCount(91663, -1);
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BLOODY_COIN_COUNT.writeId(this, buffer);
		buffer.writeLong(this._count);
	}
}
