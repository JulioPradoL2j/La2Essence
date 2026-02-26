package org.l2jmobius.gameserver.network.clientpackets.balok;

import org.l2jmobius.gameserver.managers.BattleWithBalokManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.balok.BalrogWarGetReward;

public class ExBalrogWarGetReward extends ClientPacket
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
			int availableReward = player.getVariables().getInt("BALOK_AVAILABLE_REWARD", 0);
			if (availableReward == 1)
			{
				int count = 1;
				int globalStage = BattleWithBalokManager.getInstance().getGlobalStage();
				if (globalStage < 4)
				{
					count = 30;
				}

				int reward = BattleWithBalokManager.getInstance().getReward();
				player.addItem(ItemProcessType.REWARD, reward, count, player, true);
				player.getVariables().set("BALOK_AVAILABLE_REWARD", -1);
				player.sendPacket(new BalrogWarGetReward(true));
			}
		}
	}
}
