package org.l2jmobius.gameserver.network.serverpackets.dailymission;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.gameserver.data.xml.DailyMissionData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.GameClient;
import org.l2jmobius.gameserver.network.ServerPackets;
import org.l2jmobius.gameserver.network.serverpackets.ServerPacket;

public class ExConnectedTimeAndGettableReward extends ServerPacket
{
	private final int _oneDayRewardAvailableCount;

	public ExConnectedTimeAndGettableReward(Player player)
	{
		this._oneDayRewardAvailableCount = (int) DailyMissionData.getInstance().getDailyMissionData(player).stream().filter(d -> d.getStatus(player) == 1).count();
	}

	@Override
	public void writeImpl(GameClient client, WritableBuffer buffer)
	{
		if (DailyMissionData.getInstance().isAvailable())
		{
			ServerPackets.EX_ONE_DAY_REWARD_INFO.writeId(this, buffer);
			buffer.writeInt(0);
			buffer.writeInt(this._oneDayRewardAvailableCount);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
			buffer.writeInt(0);
		}
	}
}
