package org.l2jmobius.gameserver.network.clientpackets.autopeel;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.AutoPeelRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.autopeel.ExReadyItemAutoPeel;
import org.l2jmobius.gameserver.network.serverpackets.autopeel.ExStopItemAutoPeel;

public class ExRequestStopItemAutoPeel extends ClientPacket
{
	@Override
	protected void readImpl()
	{
		this.readByte();
	}

	@Override
	protected void runImpl()
	{
		Player player = this.getPlayer();
		if (player != null)
		{
			player.removeRequest(AutoPeelRequest.class);
			player.sendPacket(new ExStopItemAutoPeel(true));
			player.sendPacket(new ExReadyItemAutoPeel(false, 0));
		}
	}
}
