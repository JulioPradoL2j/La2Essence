package org.l2jmobius.gameserver.network.serverpackets.mablegame;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExMableGameRewardItem extends ServerPacket
{
	private final int _itemId;
	private final long _itemCount;

	public ExMableGameRewardItem(int itemId, long itemCount)
	{
		this._itemId = itemId;
		this._itemCount = itemCount;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_MABLE_GAME_REWARD_ITEM.writeId(this, buffer);
		buffer.writeInt(this._itemId);
		buffer.writeLong(this._itemCount);
	}
}
