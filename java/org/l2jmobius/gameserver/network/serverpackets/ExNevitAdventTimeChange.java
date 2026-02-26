package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ExNevitAdventTimeChange extends ServerPacket
{
	private final boolean _paused;
	private final int _time;

	public ExNevitAdventTimeChange(int time)
	{
		this._time = time > 240000 ? 240000 : time;
		this._paused = this._time < 1;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESPONSE_CRYSTALITEM_INFO.writeId(this, buffer);
		buffer.writeByte(!this._paused);
		buffer.writeInt(this._time);
	}
}
