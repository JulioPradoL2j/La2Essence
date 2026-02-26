package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExChangeToAwakenedClass extends ServerPacket
{
	private final int _classId;

	public ExChangeToAwakenedClass(int classId)
	{
		this._classId = classId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHANGE_TO_AWAKENED_CLASS.writeId(this, buffer);
		buffer.writeInt(this._classId);
	}
}
