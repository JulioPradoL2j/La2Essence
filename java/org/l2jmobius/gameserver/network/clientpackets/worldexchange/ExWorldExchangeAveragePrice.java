package org.l2jmobius.gameserver.network.clientpackets.worldexchange;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.worldexchange.WorldExchangeAveragePrice;

public class ExWorldExchangeAveragePrice extends ClientPacket
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
			player.sendPacket(new WorldExchangeAveragePrice(this._itemId));
		}
	}
}
