package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;

public class ExRequestAutoFish extends ClientPacket
{
	private boolean _start;

	@Override
	protected void readImpl()
	{
		this._start = this.readByte() != 0;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._start)
			{
				player.getFishing().startFishing();
			}
			else
			{
				player.getFishing().stopFishing();
			}
		}
	}
}
