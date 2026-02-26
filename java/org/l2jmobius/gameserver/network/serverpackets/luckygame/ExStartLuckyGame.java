package org.l2jmobius.gameserver.network.serverpackets.luckygame;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.enums.LuckyGameType;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExStartLuckyGame extends ServerPacket
{
	private final LuckyGameType _type;
	private final int _ticketCount;

	public ExStartLuckyGame(LuckyGameType type, long ticketCount)
	{
		this._type = type;
		this._ticketCount = (int) ticketCount;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_START_LUCKY_GAME.writeId(this, buffer);
		buffer.writeInt(this._type.ordinal());
		buffer.writeInt(this._ticketCount);
	}
}
