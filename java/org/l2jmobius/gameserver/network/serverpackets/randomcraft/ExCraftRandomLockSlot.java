package org.l2jmobius.gameserver.network.serverpackets.randomcraft;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCraftRandomLockSlot extends ServerPacket
{
	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CRAFT_RANDOM_LOCK_SLOT.writeId(this, buffer);
		buffer.writeByte(0);
	}
}
