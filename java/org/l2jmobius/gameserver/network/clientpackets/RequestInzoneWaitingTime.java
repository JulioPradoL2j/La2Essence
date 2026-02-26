package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExInzoneWaiting;

public class RequestInzoneWaitingTime extends ClientPacket
{
	private boolean _hide;

	@Override
	protected void readImpl()
	{
		this._hide = this.readByte() == 0;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExInzoneWaiting(player, this._hide));
		}
	}
}
