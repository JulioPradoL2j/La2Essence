package org.l2jmobius.gameserver.network.serverpackets.mablegame;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExMableGameDiceResult extends ServerPacket
{
	private final int _dice;
	private final int _resultCellId;
	private final int _resultCellType;
	private final int _remainNormalDiceUseCount;

	public ExMableGameDiceResult(int dice, int resultCellId, int resultCellType, int remainNormalDiceUseCount)
	{
		this._dice = dice;
		this._resultCellId = resultCellId;
		this._resultCellType = resultCellType;
		this._remainNormalDiceUseCount = remainNormalDiceUseCount;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MABLE_GAME_DICE_RESULT.writeId(this, buffer);
		buffer.writeInt(this._dice);
		buffer.writeInt(this._resultCellId);
		buffer.writeByte(this._resultCellType);
		buffer.writeInt(this._remainNormalDiceUseCount);
	}
}
