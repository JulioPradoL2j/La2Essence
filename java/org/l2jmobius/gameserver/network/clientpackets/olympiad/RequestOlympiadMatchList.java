package org.l2jmobius.gameserver.network.clientpackets.olympiad;

import org.l2jmobius.gameserver.handler.BypassHandler;
import org.l2jmobius.gameserver.handler.IBypassHandler;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;

public class RequestOlympiadMatchList extends ClientPacket
{
 

	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null && player.inObserverMode())
		{
			IBypassHandler handler = BypassHandler.getInstance().getHandler("arenalist");
			if (handler != null)
			{
				handler.onCommand("arenalist", player, null);
			}
		}
	}
}
