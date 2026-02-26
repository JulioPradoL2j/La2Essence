package org.l2jmobius.gameserver.model.actor.tasks.player;

import org.l2jmobius.gameserver.model.actor.Player;

public class SitDownTask implements Runnable
{
	private final Player _player;

	public SitDownTask(Player player)
	{
		this._player = player;
	}

	@Override
	public void run()
	{
		if (this._player != null)
		{
			this._player.setBlockActions(true);
			this._player.setSittingProgress(false);
		}
	}
}
