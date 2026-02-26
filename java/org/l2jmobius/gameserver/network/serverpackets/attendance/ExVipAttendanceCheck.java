package org.l2jmobius.gameserver.network.serverpackets.attendance;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExVipAttendanceCheck extends ServerPacket
{
	private final boolean _available;

	public ExVipAttendanceCheck(boolean available)
	{
		this._available = available;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_VIP_ATTENDANCE_CHECK.writeId(this, buffer);
		buffer.writeByte(this._available);
	}
}
