package org.l2jmobius.gameserver.network.serverpackets.prison;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPrisonUserEnter extends ServerPacket
{
	private final int _prisonType;

	public ExPrisonUserEnter(int prisonType)
	{
		this._prisonType = prisonType;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PRISON_USER_ENTER.writeId(this, buffer);
		buffer.writeInt(this._prisonType);
	}
}
