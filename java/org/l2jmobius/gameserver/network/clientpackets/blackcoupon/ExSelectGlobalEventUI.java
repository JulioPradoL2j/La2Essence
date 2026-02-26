package org.l2jmobius.gameserver.network.clientpackets.blackcoupon;

import org.l2jmobius.gameserver.data.xml.MultisellData;
import org.l2jmobius.gameserver.managers.events.BlackCouponManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExSelectGlobalEventUI extends ClientPacket
{
	private int _eventIndex;

	@Override
	public void readImpl()
	{
		this._eventIndex = this.readInt();
	}

	@Override
	public void runImpl()
	{
		Player player = this.getClient().getPlayer();
		if (player != null)
		{
			if (this._eventIndex == 1000001)
			{
				MultisellData.getInstance().separateAndSend(BlackCouponManager.getInstance().getMultisellId(), player, null, false);
			}
		}
	}
}
