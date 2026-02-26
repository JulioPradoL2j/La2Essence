package org.l2jmobius.gameserver.network.serverpackets.compound;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExEnchantSucess extends ServerPacket
{
	private final int _itemId;
	private final int _enchantLevel;

	public ExEnchantSucess(int itemId, int enchantLevel)
	{
		this._itemId = itemId;
		this._enchantLevel = enchantLevel;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_ENCHANT_SUCCESS.writeId(this, buffer);
		buffer.writeInt(this._itemId);
		buffer.writeInt(this._enchantLevel);
	}
}
