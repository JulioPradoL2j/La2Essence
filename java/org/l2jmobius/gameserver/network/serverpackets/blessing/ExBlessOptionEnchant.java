package org.l2jmobius.gameserver.network.serverpackets.blessing;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExBlessOptionEnchant extends ServerPacket
{
	private final int _scroll;
	private final int _result;

	public ExBlessOptionEnchant(int scroll, int result)
	{
		this._scroll = scroll;
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_BLESS_OPTION_ENCHANT.writeId(this, buffer);
		buffer.writeInt(this._scroll);
		buffer.writeInt(this._result);
	}
}
