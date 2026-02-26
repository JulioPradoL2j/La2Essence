package org.l2jmobius.gameserver.model.actor.tasks.player;

import org.l2jmobius.gameserver.model.actor.Player;

public class RentPetTask implements Runnable
{
	private final Player _player;

	public RentPetTask(Player player)
	{
		this._player = player;
	}

	@Override
	public void run()
	{
		if (this._player != null)
		{
			this._player.stopRentPet();
		}
	}
}
