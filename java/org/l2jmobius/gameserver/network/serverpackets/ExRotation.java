package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExRotation extends ServerPacket
{
	private final int _charId;
	private final int _heading;

	public ExRotation(int charId, int heading)
	{
		this._charId = charId;
		this._heading = heading;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ROTATION.writeId(this, buffer);
		buffer.writeInt(this._charId);
		buffer.writeInt(this._heading);
	}
}
