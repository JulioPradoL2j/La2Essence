package org.l2jmobius.gameserver.network.clientpackets.primeshop;

import org.l2jmobius.gameserver.data.xml.PrimeShopData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestBRProductInfo extends ClientPacket
{
	private int _brId;

	@Override
	protected void readImpl()
	{
		this._brId = this.readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			PrimeShopData.getInstance().showProductInfo(player, this._brId);
		}
	}
}
