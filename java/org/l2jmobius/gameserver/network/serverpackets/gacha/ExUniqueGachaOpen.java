package org.l2jmobius.gameserver.network.serverpackets.gacha;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExUniqueGachaOpen extends ServerPacket
{
	private final int _fullInfo;
	private final int _openMode;

	public ExUniqueGachaOpen(int fullInfo, int openMode)
	{
		this._fullInfo = fullInfo;
		this._openMode = openMode;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_UNIQUE_GACHA_OPEN.writeId(this, buffer);
		buffer.writeByte(this._fullInfo);
		buffer.writeInt(this._openMode);
	}
}
