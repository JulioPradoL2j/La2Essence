package org.l2jmobius.gameserver.network.clientpackets.penaltyitemdrop;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.penaltyitemdrop.ExPenaltyItemList;

public class ExRequestPenaltyItemList extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	public void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExPenaltyItemList(player));
		}
	}
}
