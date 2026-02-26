package org.l2jmobius.gameserver.network.clientpackets.crossevent;

import java.util.ArrayList;
import java.util.List;

import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.managers.events.CrossEventManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.CrossEventAdvancedRewardHolder;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.model.item.holders.ItemHolder;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.crossevent.ExCrossEventInfo;
import org.l2jmobius.gameserver.network.serverpackets.crossevent.ExCrossEventRareReward;

public class RequestCrossEventRareReward extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			CrossEventAdvancedRewardHolder item = this.getRareReward();
			if (item != null)
			{
				ItemHolder reward = new ItemHolder(item.getItemId(), item.getCount());
				player.sendPacket(new ExCrossEventRareReward(true, reward.getId()));
				player.setCrossAdvancedRewardCount(-1);
				player.addItem(ItemProcessType.REWARD, reward, player, true);
				player.sendPacket(new ExCrossEventInfo(player));
			}
		}
	}

	private CrossEventAdvancedRewardHolder getRareReward()
	{
		List<CrossEventAdvancedRewardHolder> tempList = new ArrayList<>();

		for (CrossEventAdvancedRewardHolder reward : CrossEventManager.getInstance().getAdvancedRewardList())
		{
			if (Rnd.get(100000) <= reward.getChance())
			{
				tempList.add(reward);
			}
		}

		return tempList.isEmpty() ? this.getRareReward() : tempList.get(Rnd.get(0, tempList.size() - 1));
	}
}
