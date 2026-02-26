package org.l2jmobius.gameserver.network.clientpackets.quest;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.quest.ExQuestNotificationAll;

public class RequestExQuestNotificationAll extends ClientPacket
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
			player.sendPacket(new ExQuestNotificationAll(player));
		}
	}
}
