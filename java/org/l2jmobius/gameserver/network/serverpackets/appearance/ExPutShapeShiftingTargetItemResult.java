package org.l2jmobius.gameserver.network.serverpackets.appearance;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExPutShapeShiftingTargetItemResult extends ServerPacket
{
	public static final int RESULT_FAILED = 0;
	public static final int RESULT_SUCCESS = 1;
	public static final ExPutShapeShiftingTargetItemResult FAILED = new ExPutShapeShiftingTargetItemResult(0, 0L);
	private final int _resultId;
	private final long _price;

	public ExPutShapeShiftingTargetItemResult(int resultId, long price)
	{
		this._resultId = resultId;
		this._price = price;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_PUT_SHAPE_SHIFTING_TARGET_ITEM_RESULT.writeId(this, buffer);
		buffer.writeInt(this._resultId);
		buffer.writeLong(this._price);
	}
}
