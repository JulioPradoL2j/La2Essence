package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.enums.CharacterDeleteFailType;

public class CharDeleteFail extends ServerPacket
{
	private final int _error;

	public CharDeleteFail(CharacterDeleteFailType type)
	{
		this._error = type.ordinal();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.CHARACTER_DELETE_FAIL.writeId(this, buffer);
		buffer.writeInt(this._error);
	}
}
