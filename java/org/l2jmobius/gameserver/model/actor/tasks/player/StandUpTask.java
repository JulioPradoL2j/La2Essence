package org.l2jmobius.gameserver.model.actor.tasks.player;

import org.l2jmobius.gameserver.ai.Intention;
import org.l2jmobius.gameserver.model.actor.Player;

public class StandUpTask implements Runnable
{
	private final Player _player;

	public StandUpTask(Player player)
	{
		this._player = player;
	}

	@Override
	public void run()
	{
		if (this._player != null)
		{
			this._player.setBlockActions(false);
			this._player.setSitting(false);
			this._player.setSittingProgress(false);
			this._player.getAI().setIntention(Intention.IDLE);
		}
	}
}
