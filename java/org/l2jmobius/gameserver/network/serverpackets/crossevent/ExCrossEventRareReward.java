package org.l2jmobius.gameserver.network.serverpackets.crossevent;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCrossEventRareReward extends ServerPacket
{
	private final int _itemId;
	private final boolean _isAvailable;

	public ExCrossEventRareReward(boolean isAvailable, int itemId)
	{
		this._isAvailable = isAvailable;
		this._itemId = itemId;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CROSS_EVENT_RARE_REWARD.writeId(this, buffer);
		buffer.writeByte(this._isAvailable);
		buffer.writeLong(this._itemId);
	}
}
