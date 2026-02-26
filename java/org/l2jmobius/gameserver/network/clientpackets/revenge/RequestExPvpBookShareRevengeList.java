package org.l2jmobius.gameserver.network.clientpackets.revenge;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.revenge.ExPvpBookShareRevengeList;

public class RequestExPvpBookShareRevengeList extends ClientPacket
{
	private int _objectId;

	@Override
	protected void readImpl()
	{
		this._objectId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (this._objectId == player.getObjectId())
			{
				player.sendPacket(new ExPvpBookShareRevengeList(player));
			}
		}
	}
}
