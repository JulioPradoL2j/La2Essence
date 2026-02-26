package org.l2jmobius.gameserver.network.clientpackets.worldexchange;

import java.util.Collections;

import org.l2jmobius.gameserver.config.WorldExchangeConfig;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.worldexchange.WorldExchangeItemList;
import org.l2jmobius.gameserver.network.serverpackets.worldexchange.WorldExchangeSettleList;

public class ExWorldExchangeSettleList extends ClientPacket
{
	@Override
	protected void readImpl()
	{
		this.readByte();
	}

	@Override
	protected void runImpl()
	{
		if (WorldExchangeConfig.ENABLE_WORLD_EXCHANGE)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				player.sendPacket(new WorldExchangeItemList(Collections.emptyList(), null, 0));
				player.sendPacket(new WorldExchangeSettleList(player));
			}
		}
	}
}
