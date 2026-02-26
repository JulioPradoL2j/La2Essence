package org.l2jmobius.gameserver.network.clientpackets.subjugation;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.subjugation.ExSubjugationList;

public class RequestSubjugationList extends ClientPacket
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
			player.sendPacket(new ExSubjugationList(player.getPurgePoints()));
		}
	}
}
