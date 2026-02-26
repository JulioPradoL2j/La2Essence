package org.l2jmobius.gameserver.network.serverpackets.autopeel;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExReadyItemAutoPeel extends ServerPacket
{
	private final boolean _result;
	private final int _itemObjectId;

	public ExReadyItemAutoPeel(boolean result, int itemObjectId)
	{
		this._result = result;
		this._itemObjectId = itemObjectId;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_READY_ITEM_AUTO_PEEL.writeId(this, buffer);
		buffer.writeByte(this._result);
		buffer.writeInt(this._itemObjectId);
	}
}
