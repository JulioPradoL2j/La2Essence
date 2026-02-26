package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPledgeCoinInfo extends ServerPacket
{
	private final long _count;

	public ExPledgeCoinInfo(Player player)
	{
		this._count = player.getHonorCoins();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PLEDGE_COIN_INFO.writeId(this, buffer);
		buffer.writeLong(this._count);
	}
}
