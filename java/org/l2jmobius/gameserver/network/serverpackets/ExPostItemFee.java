package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPostItemFee extends ServerPacket
{
	private final long _fee;

	public ExPostItemFee(long fee)
	{
		this._fee = fee;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_POST_ITEM_FEE.writeId(this, buffer);
		buffer.writeLong(this._fee);
	}
}
