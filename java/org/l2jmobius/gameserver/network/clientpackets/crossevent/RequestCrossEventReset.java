package org.l2jmobius.gameserver.network.clientpackets.crossevent;

import org.l2jmobius.gameserver.managers.events.CrossEventManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.crossevent.ExCrossEventInfo;
import org.l2jmobius.gameserver.network.serverpackets.crossevent.ExCrossEventReset;

public class RequestCrossEventReset extends ClientPacket
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
			int resetCount = player.getPlayerResetCount();
			if (resetCount >= 1)
			{
				player.sendPacket(new ExCrossEventReset());
				player.setPlayerResetCount(resetCount - 1);
				player.getCrossEventCells().clear();
				CrossEventManager.getInstance().resetAdvancedRewards(player);
				player.sendPacket(new ExCrossEventInfo(player));
				player.getInventory().destroyItemByItemId(ItemProcessType.FEE, 57, CrossEventManager.getInstance().getResetCostAmount(), player, null);
			}
		}
	}
}
