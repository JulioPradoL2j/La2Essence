package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;

public class StopMoveToward extends ClientPacket
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
			player.broadcastPacket(new org.l2jmobius.gameserver.network.serverpackets.MoveToLocation(player));
			player.stopMove(player.getLocation());
		}
	}
}
