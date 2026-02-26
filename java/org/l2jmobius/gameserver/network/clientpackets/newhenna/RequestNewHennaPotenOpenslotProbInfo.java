package org.l2jmobius.gameserver.network.clientpackets.newhenna;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.newhenna.NewHennaPotenOpenslotProbInfo;

public class RequestNewHennaPotenOpenslotProbInfo extends ClientPacket
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
			player.sendPacket(new NewHennaPotenOpenslotProbInfo(player, this._slot));
		}
	}
}
