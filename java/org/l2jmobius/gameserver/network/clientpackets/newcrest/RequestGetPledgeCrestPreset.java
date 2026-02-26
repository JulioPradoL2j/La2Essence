package org.l2jmobius.gameserver.network.clientpackets.newcrest;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.newcrest.GetPledgeCrestPreset;

public class RequestGetPledgeCrestPreset extends ClientPacket
{
	private int _clanId;
	private int _emblemId;

	@Override
	protected void readImpl()
	{
		this._clanId = this.readInt();
		this._emblemId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new GetPledgeCrestPreset(this._clanId, this._emblemId));
		}
	}
}
