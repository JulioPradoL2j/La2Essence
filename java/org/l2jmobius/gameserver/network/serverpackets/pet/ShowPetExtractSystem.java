package org.l2jmobius.gameserver.network.serverpackets.pet;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ShowPetExtractSystem extends ServerPacket
{
	public static final ShowPetExtractSystem STATIC_PACKET = new ShowPetExtractSystem();

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHOW_PET_EXTRACT_SYSTEM.writeId(this, buffer);
		buffer.writeInt(0);
	}
}
