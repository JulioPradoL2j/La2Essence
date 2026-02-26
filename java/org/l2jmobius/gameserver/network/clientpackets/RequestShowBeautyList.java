package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.ExResponseBeautyList;

public class RequestShowBeautyList extends ClientPacket
{
	private int _type;

	@Override
	protected void readImpl()
	{
		this._type = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new ExResponseBeautyList(player, this._type));
		}
	}
}
