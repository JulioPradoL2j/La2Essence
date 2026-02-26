package org.l2jmobius.gameserver.network.serverpackets.compound;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExEnchantFail extends ServerPacket
{
	public static final ExEnchantFail STATIC_PACKET = new ExEnchantFail(0, 0);
	private final int _itemOne;
	private final int _itemTwo;

	public ExEnchantFail(int itemOne, int itemTwo)
	{
		this._itemOne = itemOne;
		this._itemTwo = itemTwo;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ENCHANT_FAIL.writeId(this, buffer);
		buffer.writeInt(this._itemOne);
		buffer.writeInt(this._itemTwo);
	}
}
