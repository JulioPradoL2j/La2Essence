package org.l2jmobius.gameserver.network.clientpackets.compound;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.CompoundRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestNewEnchantClose extends ClientPacket
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
			player.removeRequest(CompoundRequest.class);
		}
	}
}
