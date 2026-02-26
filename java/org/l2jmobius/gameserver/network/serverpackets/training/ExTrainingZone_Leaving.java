package org.l2jmobius.gameserver.network.serverpackets.training;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExTrainingZone_Leaving extends ServerPacket
{
	public static final ExTrainingZone_Leaving STATIC_PACKET = new ExTrainingZone_Leaving();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_TRAININGZONE_LEAVING.writeId(this, buffer);
	}
}
