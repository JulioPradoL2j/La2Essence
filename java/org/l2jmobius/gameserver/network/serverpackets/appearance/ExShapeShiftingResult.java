package org.l2jmobius.gameserver.network.serverpackets.appearance;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExShapeShiftingResult extends ServerPacket
{
	public static final int RESULT_FAILED = 0;
	public static final int RESULT_SUCCESS = 1;
	public static final int RESULT_CLOSE = 2;
	public static final ExShapeShiftingResult FAILED = new ExShapeShiftingResult(0, 0, 0);
	public static final ExShapeShiftingResult CLOSE = new ExShapeShiftingResult(2, 0, 0);
	private final int _result;
	private final int _targetItemId;
	private final int _extractItemId;

	public ExShapeShiftingResult(int result, int targetItemId, int extractItemId)
	{
		this._result = result;
		this._targetItemId = targetItemId;
		this._extractItemId = extractItemId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_SHAPE_SHIFTING_RESULT.writeId(this, buffer);
		buffer.writeInt(this._result);
		buffer.writeInt(this._targetItemId);
		buffer.writeInt(this._extractItemId);
	}
}
