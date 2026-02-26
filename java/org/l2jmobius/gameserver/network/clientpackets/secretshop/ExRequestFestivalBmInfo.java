package org.l2jmobius.gameserver.network.clientpackets.secretshop;

import org.l2jmobius.gameserver.managers.events.SecretShopEventManager;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class ExRequestFestivalBmInfo extends ClientPacket
{
	private boolean _isOpenWindow;

	@Override
	protected void readImpl()
	{
		this._isOpenWindow = this.readByte() != 0;
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			SecretShopEventManager.getInstance().show(player, this._isOpenWindow);
		}
	}
}
