package org.l2jmobius.gameserver.network.clientpackets.newskillenchant;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.item.enums.ItemProcessType;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.newskillenchant.ExSpExtractItem;

public class RequestExSpExtractItem extends ClientPacket
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
			if (player.getSp() >= 50000000L && player.getAdena() >= 3000000L && player.getVariables().getInt("DAILY_EXTRACT_ITEM98232", 5) > 0)
			{
				player.removeExpAndSp(0L, 50000000L);
				player.broadcastUserInfo();
				player.reduceAdena(ItemProcessType.FEE, 3000000L, null, true);
				player.addItem(ItemProcessType.REWARD, 98232, 1L, null, true);
				int current = player.getVariables().getInt("DAILY_EXTRACT_ITEM98232", 5);
				player.getVariables().set("DAILY_EXTRACT_ITEM98232", current - 1);
				player.sendPacket(new ExSpExtractItem());
			}
		}
	}
}
