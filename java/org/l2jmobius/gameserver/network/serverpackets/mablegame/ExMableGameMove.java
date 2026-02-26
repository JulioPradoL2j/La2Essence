package org.l2jmobius.gameserver.network.serverpackets.mablegame;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExMableGameMove extends ServerPacket
{
	private final int _moveDelta;
	private final int _resultCellId;
	private final int _resultCellType;

	public ExMableGameMove(int moveDelta, int resultCellId, int resultCellType)
	{
		this._moveDelta = moveDelta;
		this._resultCellId = resultCellId;
		this._resultCellType = resultCellType;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MABLE_GAME_MOVE.writeId(this, buffer);
		buffer.writeInt(this._moveDelta);
		buffer.writeInt(this._resultCellId);
		buffer.writeByte(this._resultCellType);
	}
}
