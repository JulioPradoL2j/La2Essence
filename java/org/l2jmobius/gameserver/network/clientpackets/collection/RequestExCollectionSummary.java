package org.l2jmobius.gameserver.network.clientpackets.collection;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.collection.ExCollectionSummary;

public class RequestExCollectionSummary extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getClient().getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExCollectionSummary(player));
		}
	}
}
