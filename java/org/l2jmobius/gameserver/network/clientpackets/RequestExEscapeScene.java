package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.MovieHolder;

public class RequestExEscapeScene extends ClientPacket
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
			MovieHolder holder = player.getMovieHolder();
			if (holder != null)
			{
				holder.playerEscapeVote(player);
			}
		}
	}
}
