package org.l2jmobius.gameserver.network.serverpackets.olympiad;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.model.olympiad.OlympiadMode;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExOlympiadMode extends ServerPacket
{
	private final OlympiadMode _mode;

	public ExOlympiadMode(OlympiadMode mode)
	{
		this._mode = mode;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_OLYMPIAD_MODE.writeId(this, buffer);
		buffer.writeByte(this._mode.ordinal());
	}
}
