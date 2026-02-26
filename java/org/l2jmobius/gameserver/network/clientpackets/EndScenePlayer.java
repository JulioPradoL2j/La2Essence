package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.holders.player.MovieHolder;

public class EndScenePlayer extends ClientPacket
{
	private int _movieId;

	@Override
	protected void readImpl()
	{
		this._movieId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && this._movieId != 0)
		{
			MovieHolder holder = player.getMovieHolder();
			if (holder != null && holder.getMovie().getClientId() == this._movieId)
			{
				player.stopMovie();
			}
		}
	}
}
