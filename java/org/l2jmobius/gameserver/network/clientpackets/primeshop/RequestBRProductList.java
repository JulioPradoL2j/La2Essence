package org.l2jmobius.gameserver.network.clientpackets.primeshop;

import org.l2jmobius.gameserver.data.xml.PrimeShopData;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.PacketLogger;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.primeshop.ExBRProductList;

public class RequestBRProductList extends ClientPacket
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
			switch (this._type)
			{
				case 0:
					player.sendPacket(new ExBRProductList(player, 0, PrimeShopData.getInstance().getPrimeItems().values()));
				case 1:
				case 2:
					break;
				default:
					PacketLogger.warning(player + " send unhandled product list type: " + this._type);
			}
		}
	}
}
