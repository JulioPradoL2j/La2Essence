package org.l2jmobius.gameserver.network.serverpackets.blessing;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExBlessOptionCancel extends ServerPacket
{
	private final int _result;

	public ExBlessOptionCancel(int result)
	{
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BLESS_OPTION_CANCEL.writeId(this, buffer);
		buffer.writeByte(this._result);
	}
}
