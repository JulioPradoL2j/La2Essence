package org.l2jmobius.gameserver.network.serverpackets.autopeel;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExStopItemAutoPeel extends ServerPacket
{
	private final boolean _result;

	public ExStopItemAutoPeel(boolean result)
	{
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_STOP_ITEM_AUTO_PEEL.writeId(this, buffer);
		buffer.writeByte(this._result);
	}
}
