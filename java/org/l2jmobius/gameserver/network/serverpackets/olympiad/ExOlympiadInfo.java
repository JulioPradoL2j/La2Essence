package org.l2jmobius.gameserver.network.serverpackets.olympiad;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExOlympiadInfo extends ServerPacket
{
	private final int _open;
	private final int _remainTime;

	public ExOlympiadInfo(int open, int remainTime)
	{
		this._open = open;
		this._remainTime = remainTime;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_OLYMPIAD_INFO.writeId(this, buffer);
		buffer.writeByte(this._open);
		buffer.writeInt(this._remainTime);
		buffer.writeByte(1);
	}
}
