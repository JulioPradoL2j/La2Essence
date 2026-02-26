package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.PledgeCrest;

public class RequestPledgeCrest extends ClientPacket
{
	private int _crestId;
	private int _clanId;

	@Override
	protected void readImpl()
	{
		this._clanId = this.readInt();
		this._crestId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new PledgeCrest(this._crestId, this._clanId));
		}
	}
}
