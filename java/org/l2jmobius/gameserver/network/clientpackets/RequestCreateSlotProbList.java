package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.CreateSlotProbList;

public class RequestCreateSlotProbList extends ClientPacket
{
	private int _slot;

	@Override
	protected void readImpl()
	{
		this._slot = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new CreateSlotProbList(player, this._slot));
		}
	}
}
