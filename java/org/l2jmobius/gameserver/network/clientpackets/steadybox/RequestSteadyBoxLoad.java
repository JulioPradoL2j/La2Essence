package org.l2jmobius.gameserver.network.clientpackets.steadybox;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.achievementbox.ExSteadyAllBoxUpdate;

public class RequestSteadyBoxLoad extends ClientPacket
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
			player.getAchievementBox().tryFinishBox();
			player.sendPacket(new ExSteadyAllBoxUpdate(player));
		}
	}
}
