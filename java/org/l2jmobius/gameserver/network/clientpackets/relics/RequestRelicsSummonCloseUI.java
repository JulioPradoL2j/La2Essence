package org.l2jmobius.gameserver.network.clientpackets.relics;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.RelicSummonRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestRelicsSummonCloseUI extends ClientPacket
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
			player.removeRequest(RelicSummonRequest.class);
		}
	}
}
