package org.l2jmobius.gameserver.network.serverpackets.pet;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ResultPetExtractSystem extends ServerPacket
{
	private final boolean _success;

	public ResultPetExtractSystem(boolean success)
	{
		this._success = success;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_RESULT_PET_EXTRACT_SYSTEM.writeId(this, buffer);
		buffer.writeInt(this._success);
	}
}
