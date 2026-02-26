package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class TradeDone extends ServerPacket
{
	private final int _num;

	public TradeDone(int num)
	{
		this._num = num;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.TRADE_DONE.writeId(this, buffer);
		buffer.writeInt(this._num);
	}
}
