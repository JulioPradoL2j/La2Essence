package org.l2jmobius.gameserver.network.clientpackets.friend;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.friend.ExBlockDetailInfo;

public class RequestBlockDetailInfo extends ClientPacket
{
	private String _name;

	@Override
	protected void readImpl()
	{
		this._name = this.readString();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExBlockDetailInfo(player, this._name));
		}
	}
}
