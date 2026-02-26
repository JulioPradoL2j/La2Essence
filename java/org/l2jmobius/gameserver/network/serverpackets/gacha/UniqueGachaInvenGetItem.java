package org.l2jmobius.gameserver.network.serverpackets.gacha;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class UniqueGachaInvenGetItem extends ServerPacket
{
	private final int _result;

	public UniqueGachaInvenGetItem(boolean result)
	{
		this._result = result ? 1 : 0;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_UNIQUE_GACHA_INVEN_GET_ITEM.writeId(this, buffer);
		buffer.writeByte(this._result);
	}
}
