package org.l2jmobius.gameserver.network.serverpackets.enchant.challengepoint;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExSetEnchantChallengePoint extends ServerPacket
{
	private final boolean _result;

	public ExSetEnchantChallengePoint(boolean result)
	{
		this._result = result;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SET_ENCHANT_CHALLENGE_POINT.writeId(this, buffer);
		buffer.writeByte(this._result);
	}
}
