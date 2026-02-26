package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;

public class ShowCalculator extends ServerPacket
{
	private final int _calculatorId;

	public ShowCalculator(int calculatorId)
	{
		this._calculatorId = calculatorId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.SHOW_CALC.writeId(this, buffer);
		buffer.writeInt(this._calculatorId);
	}
}
