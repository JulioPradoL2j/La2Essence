package org.l2jmobius.gameserver.network.clientpackets.castlewar;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.castlewar.MercenaryPledgeMemberList;

public class ExPledgeMercenaryMemberList extends ClientPacket
{
	private int _castleId;
	private int _pledgeId;

	@Override
	protected void readImpl()
	{
		this._castleId = this.readInt();
		this._pledgeId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new MercenaryPledgeMemberList(this._castleId, this._pledgeId));
		}
	}
}
