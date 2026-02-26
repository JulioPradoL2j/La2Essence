package org.l2jmobius.gameserver.network.clientpackets;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.serverpackets.CreateItemProbList;

public class RequestCreateItemProbList extends ClientPacket
{
	private int _itemId;

	@Override
	protected void readImpl()
	{
		this._itemId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.sendPacket(new CreateItemProbList(this._itemId));
		}
	}
}
