package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExNevitAdventPointInfoPacket extends ServerPacket
{
	private final int _points;

	public ExNevitAdventPointInfoPacket(int points)
	{
		this._points = points;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BR_AGATHION_ENERGY_INFO.writeId(this, buffer);
		buffer.writeInt(this._points);
	}
}
