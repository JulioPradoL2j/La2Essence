package org.l2jmobius.gameserver.network.serverpackets.enchant;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPutEnchantScrollItemResult extends ServerPacket
{
	private final int _result;

	public ExPutEnchantScrollItemResult(int result)
	{
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PUT_ENCHANT_SCROLL_ITEM_RESULT.writeId(this, buffer);
		buffer.writeInt(this._result);
	}
}
