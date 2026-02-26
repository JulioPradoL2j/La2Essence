package org.l2jmobius.gameserver.network.clientpackets.crossevent;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.crossevent.ExCrossEventData;
import org.l2jmobius.gameserver.network.serverpackets.crossevent.ExCrossEventInfo;

public class RequestCrossEventInfo extends ClientPacket
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
			player.sendPacket(new ExCrossEventData());
			player.sendPacket(new ExCrossEventInfo(player));
		}
	}
}
