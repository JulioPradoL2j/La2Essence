package org.l2jmobius.gameserver.network.serverpackets.enchant;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExRemoveEnchantSupportItemResult extends ServerPacket
{
	public static final ExRemoveEnchantSupportItemResult STATIC_PACKET = new ExRemoveEnchantSupportItemResult();

	private ExRemoveEnchantSupportItemResult()
	{
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_REMOVE_ENCHANT_SUPPORT_ITEM_RESULT.writeId(this, buffer);
	}
}
