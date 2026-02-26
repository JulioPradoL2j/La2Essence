package org.l2jmobius.gameserver.network.clientpackets.prison;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestPrisonUserDonation extends ClientPacket
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
			player.getPrisonerInfo().requestFreedomByDonation(player);
		}
	}
}
