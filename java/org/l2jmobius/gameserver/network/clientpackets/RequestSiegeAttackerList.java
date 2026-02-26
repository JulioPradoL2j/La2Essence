package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.managers.CastleManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.siege.Castle;
import org.l2jmobius.gameserver.network.serverpackets.SiegeAttackerList;

public class RequestSiegeAttackerList extends ClientPacket
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
		Castle castle = CastleManager.getInstance().getCastleById(this._castleId);
		if (castle != null)
		{
			Player player = this.getPlayer();
			if (player != null)
			{
				player.sendPacket(new SiegeAttackerList(castle));
			}
		}
	}
}
