package org.l2jmobius.gameserver.network.serverpackets.olympiad;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExOlympiadMatchMakingResult extends ServerPacket
{
	private final int _gameRuleType;
	private final int _type;

	public ExOlympiadMatchMakingResult(int gameRuleType, int type)
	{
		this._gameRuleType = gameRuleType;
		this._type = type;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_OLYMPIAD_MATCH_MAKING_RESULT.writeId(this, buffer);
		buffer.writeByte(this._type);
		buffer.writeInt(this._gameRuleType);
	}
}
