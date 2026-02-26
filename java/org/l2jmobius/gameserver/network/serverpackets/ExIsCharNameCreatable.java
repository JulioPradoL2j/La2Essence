package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExIsCharNameCreatable extends ServerPacket
{
	private final int _allowed;

	public ExIsCharNameCreatable(int allowed)
	{
		this._allowed = allowed;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CHECK_CHAR_NAME.writeId(this, buffer);
		buffer.writeInt(this._allowed);
	}
}
