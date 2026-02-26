package org.l2jmobius.gameserver.network.clientpackets.castlewar;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.castlewar.MercenaryCastleWarCastleSiegeAttackerList;

public class ExMercenaryCastleWarCastleSiegeAttackerList extends ClientPacket
{
	private int _castleId;

	@Override
	protected void readImpl()
	{
		this._castleId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new MercenaryCastleWarCastleSiegeAttackerList(this._castleId));
		}
	}
}
