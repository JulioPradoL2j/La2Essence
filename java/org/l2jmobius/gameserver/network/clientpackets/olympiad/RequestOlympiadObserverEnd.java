package org.l2jmobius.gameserver.network.clientpackets.olympiad;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.olympiad.OlympiadMode;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.olympiad.ExOlympiadMode;

public class RequestOlympiadObserverEnd extends ClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			if (player.inObserverMode())
			{
				player.leaveOlympiadObserverMode();
			}
			else
			{
				player.sendPacket(new ExOlympiadMode(OlympiadMode.NONE));
			}
		}
	}
}
