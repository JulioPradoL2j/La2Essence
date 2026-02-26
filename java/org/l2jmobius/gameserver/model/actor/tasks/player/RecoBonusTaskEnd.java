package org.l2jmobius.gameserver.model.actor.tasks.player;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExVoteSystemInfo;

public class RecoBonusTaskEnd implements Runnable
{
	private final Player _player;

	public RecoBonusTaskEnd(Player player)
	{
		this._player = player;
	}

	@Override
	public void run()
	{
		if (this._player != null)
		{
			this._player.sendPacket(new ExVoteSystemInfo(this._player));
		}
	}
}
