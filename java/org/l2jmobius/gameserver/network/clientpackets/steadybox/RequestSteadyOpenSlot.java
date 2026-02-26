package org.l2jmobius.gameserver.network.clientpackets.steadybox;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestSteadyOpenSlot extends ClientPacket
{
	private int _slotId;

	@Override
	protected void readImpl()
	{
		this._slotId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.getAchievementBox().unlockSlot(this._slotId);
		}
	}
}
