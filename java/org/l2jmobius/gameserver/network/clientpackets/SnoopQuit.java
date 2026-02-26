package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.World;
import org.l2jmobius.gameserver.model.actor.Player;

public class SnoopQuit extends ClientPacket
{
	private int _snoopID;

	@Override
	protected void readImpl()
	{
		this._snoopID = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player target = World.getInstance().getPlayer(this._snoopID);
		if (target != null)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				target.removeSnooper(player);
				player.removeSnooped(target);
			}
		}
	}
}
