package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExPVPMatchCCMyRecord extends ServerPacket
{
	private final int _points;

	public ExPVPMatchCCMyRecord(int points)
	{
		this._points = points;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PVPMATCH_CC_MY_RECORD.writeId(this, buffer);
		buffer.writeInt(this._points);
	}
}
