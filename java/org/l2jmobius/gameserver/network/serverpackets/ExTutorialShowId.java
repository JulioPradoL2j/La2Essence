package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExTutorialShowId extends ServerPacket
{
	private final int _id;

	public ExTutorialShowId(int id)
	{
		this._id = id;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_TUTORIAL_SHOW_ID.writeId(this, buffer);
		buffer.writeInt(this._id);
	}
}
