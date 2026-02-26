package org.l2jmobius.gameserver.network.clientpackets.blessing;

import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.actor.request.BlessingItemRequest;
import org.l2jmobius.gameserver.network.clientpackets.ClientPacket;
import org.l2jmobius.gameserver.network.serverpackets.blessing.ExBlessOptionCancel;

public class RequestBlessOptionCancel extends ClientPacket
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
			player.removeRequest(BlessingItemRequest.class);
			player.sendPacket(new ExBlessOptionCancel(1));
		}
	}
}
