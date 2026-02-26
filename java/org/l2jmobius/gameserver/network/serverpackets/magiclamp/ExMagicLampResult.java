package org.l2jmobius.gameserver.network.serverpackets.magiclamp;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExMagicLampResult extends ServerPacket
{
	private final int _exp;
	private final int _grade;

	public ExMagicLampResult(int exp, int grade)
	{
		this._exp = exp;
		this._grade = grade;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MAGICLAMP_RESULT.writeId(this, buffer);
		buffer.writeLong(this._exp);
		buffer.writeInt(this._grade);
	}
}
