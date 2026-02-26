package org.l2jmobius.gameserver.network.serverpackets.secretshop;

import java.util.List;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.managers.events.SecretShopEventManager;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExFestivalBmTopItemInfo extends ServerPacket
{
	private final long _endTime;
	private final boolean _isUseFestivalBm;
	private final List<SecretShopEventManager.SecretShopRewardHolder> _activeRewards;

	public ExFestivalBmTopItemInfo(long endTime, boolean isUseFestivalBm, List<SecretShopEventManager.SecretShopRewardHolder> activeRewards)
	{
		this._endTime = endTime;
		this._isUseFestivalBm = isUseFestivalBm;
		this._activeRewards = activeRewards;
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		ServerPackets.EX_FESTIVAL_BM_TOP_ITEM_INFO.writeId(this, buffer);
		buffer.writeByte(this._isUseFestivalBm ? 1 : 2);
		buffer.writeInt(20);
		buffer.writeInt((int) this._endTime / 1000);
		buffer.writeInt(3);
		int written = 0;
		if (this._activeRewards != null)
		{
			for (SecretShopEventManager.SecretShopRewardHolder reward : this._activeRewards)
			{
				if (reward.isTopGrade())
				{
					written++;
					buffer.writeByte(reward.getGrade());
					buffer.writeInt(reward.getId());
					buffer.writeInt((int) reward.getCurrentAmount());
					buffer.writeInt((int) reward.getTotalAmount());
				}
			}
		}

		while (written < 3)
		{
			buffer.writeByte(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			written++;
		}
	}
}
