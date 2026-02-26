package org.l2jmobius.gameserver.network.serverpackets.penaltyitemdrop;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPenaltyItemRestore extends ServerPacket
{
	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PENALTY_ITEM_RESTORE.writeId(this, buffer);
		buffer.writeByte(1);
	}
}
