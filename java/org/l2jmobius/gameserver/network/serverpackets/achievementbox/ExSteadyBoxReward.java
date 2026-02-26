package org.l2jmobius.gameserver.network.serverpackets.achievementbox;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExSteadyBoxReward extends ServerPacket
{
	private final int _slotId;
	private final int _itemId;
	private final long _itemCount;

	public ExSteadyBoxReward(int slotId, int itemId, long itemCount)
	{
		this._slotId = slotId;
		this._itemId = itemId;
		this._itemCount = itemCount;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_STEADY_BOX_REWARD.writeId(this, buffer);
		buffer.writeInt(this._slotId);
		buffer.writeInt(this._itemId);
		buffer.writeLong(this._itemCount);
		buffer.writeInt(0);
	}
}
