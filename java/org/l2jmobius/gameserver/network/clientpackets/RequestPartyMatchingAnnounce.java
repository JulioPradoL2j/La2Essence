package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.BlockList;
import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExPartyRoomAnnounce;

public class RequestPartyMatchingAnnounce extends ClientPacket
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
			for (Player worldPlayers : World.getInstance().getPlayers())
			{
				if (!BlockList.isBlocked(worldPlayers, player))
				{
					worldPlayers.sendPacket(new ExPartyRoomAnnounce(player));
				}
			}
		}
	}
}
