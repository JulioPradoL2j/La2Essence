package org.l2jmobius.gameserver.network.clientpackets.dailymission;

import java.util.Collection;

import org.l2jmobius.commons.threads.ThreadPool;
import org.l2jmobius.gameserver.data.xml.DailyMissionData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.DailyMissionDataHolder;
import org.l2jmobius.gameserver.model.actor.request.RewardRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.dailymission.ExConnectedTimeAndGettableReward;
import org.l2jmobius.gameserver.network.serverpackets.dailymission.ExOneDayReceiveRewardList;

public class RequestOneDayRewardReceive extends ClientPacket
{
	private int _id;

	@Override
	protected void readImpl()
	{
		this._id = this.readShort();
	}

	@Override
	protected void runImpl()
	{
		if (this.getClient().getFloodProtectors().canPerformPlayerAction())
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				if (!player.hasRequest(RewardRequest.class))
				{
					player.addRequest(new RewardRequest(player));
					Collection<DailyMissionDataHolder> rewards = DailyMissionData.getInstance().getDailyMissionData(this._id);
					if (rewards != null && !rewards.isEmpty())
					{
						for (DailyMissionDataHolder holder : rewards)
						{
							if (holder.isDisplayable(player))
							{
								holder.requestReward(player);
							}
						}

						player.sendPacket(new ExOneDayReceiveRewardList(player, true));
						player.sendPacket(new ExConnectedTimeAndGettableReward(player));
						ThreadPool.schedule(() -> player.removeRequest(RewardRequest.class), 300L);
					}
					else
					{
						player.removeRequest(RewardRequest.class);
					}
				}
			}
		}
	}
}
