package org.l2jmobius.gameserver.network.serverpackets.crossevent;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExCrossEventNormalReward extends ServerPacket
{
	private final int _vertical;
	private final int _horizontal;
	private final int _rewardAmount;

	public ExCrossEventNormalReward(int vertical, int horizontal, int rewardAmount)
	{
		this._vertical = vertical;
		this._horizontal = horizontal;
		this._rewardAmount = rewardAmount;
	}

	@Override
	protected void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_CROSS_EVENT_NORMAL_REWARD.writeId(this, buffer);
		buffer.writeByte(1);
		buffer.writeInt(this._vertical);
		buffer.writeInt(this._horizontal);
		buffer.writeInt(this._rewardAmount);
	}
}
