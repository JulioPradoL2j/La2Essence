package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExNevitAdventEffect extends ServerPacket
{
	private final int _timeLeft;

	public ExNevitAdventEffect(int timeLeft)
	{
		this._timeLeft = timeLeft;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_CHANNELING_EFFECT.writeId(this, buffer);
		buffer.writeInt(this._timeLeft);
	}
}
