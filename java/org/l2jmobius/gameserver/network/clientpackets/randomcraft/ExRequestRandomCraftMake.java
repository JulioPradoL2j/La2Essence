package org.l2jmobius.gameserver.network.clientpackets.randomcraft;

import org.l2jmobius.gameserver.config.RandomCraftConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.itemcontainer.PlayerRandomCraft;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExRequestRandomCraftMake extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		if (RandomCraftConfig.ENABLE_RANDOM_CRAFT)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				PlayerRandomCraft rc = player.getRandomCraft();
				rc.make();
			}
		}
	}
}
